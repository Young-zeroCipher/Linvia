import 'react-native-get-random-values';

import AsyncStorage from '@react-native-async-storage/async-storage';
import * as SecureStore from 'expo-secure-store';
import * as aesjs from 'aes-js';

/**
 * Supabase sessions can exceed SecureStore's per-value size limit. Keep the
 * session encrypted in AsyncStorage and keep its AES key in SecureStore.
 */
export class SecureSessionStorage {
  private async encrypt(key: string, value: string): Promise<string> {
    const encryptionKey = globalThis.crypto.getRandomValues(new Uint8Array(32));
    const cipher = new aesjs.ModeOfOperation.ctr(encryptionKey, new aesjs.Counter(1));
    const encrypted = cipher.encrypt(aesjs.utils.utf8.toBytes(value));

    await SecureStore.setItemAsync(key, aesjs.utils.hex.fromBytes(encryptionKey));
    return aesjs.utils.hex.fromBytes(encrypted);
  }

  private async decrypt(key: string, value: string): Promise<string | null> {
    const encryptionKey = await SecureStore.getItemAsync(key);
    if (!encryptionKey) {
      await AsyncStorage.removeItem(key);
      return null;
    }

    const cipher = new aesjs.ModeOfOperation.ctr(
      aesjs.utils.hex.toBytes(encryptionKey),
      new aesjs.Counter(1),
    );
    const decrypted = cipher.decrypt(aesjs.utils.hex.toBytes(value));
    return aesjs.utils.utf8.fromBytes(decrypted);
  }

  async getItem(key: string): Promise<string | null> {
    const encrypted = await AsyncStorage.getItem(key);
    return encrypted ? this.decrypt(key, encrypted) : null;
  }

  async setItem(key: string, value: string): Promise<void> {
    const encrypted = await this.encrypt(key, value);
    await AsyncStorage.setItem(key, encrypted);
  }

  async removeItem(key: string): Promise<void> {
    await Promise.all([
      AsyncStorage.removeItem(key),
      SecureStore.deleteItemAsync(key),
    ]);
  }
}
