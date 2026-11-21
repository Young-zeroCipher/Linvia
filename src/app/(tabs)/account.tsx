import { MaterialCommunityIcons } from '@expo/vector-icons';
import type { User } from '@supabase/supabase-js';
import { useEffect, useState } from 'react';
import { ActivityIndicator, Pressable, ScrollView, StyleSheet, Text, TextInput, View } from 'react-native';
import { useAuth } from '../../features/auth/AuthProvider';
import { isSupabaseConfigured, supabase } from '../../features/auth/supabase';
import { colors } from '../../shared/theme/colors';
import { spacing } from '../../shared/theme/spacing';

type Profile = { display_name: string };
type AuthMode = 'sign-in' | 'sign-up';

export default function AccountScreen() {
  const { user, loading } = useAuth();
  const [mode, setMode] = useState<AuthMode>('sign-in');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [displayName, setDisplayName] = useState('');
  const [profile, setProfile] = useState<Profile | null>(null);
  const [profileLoading, setProfileLoading] = useState(false);
  const [busy, setBusy] = useState(false);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  useEffect(() => {
    if (!user || !supabase) {
      setProfile(null);
      return;
    }

    let active = true;
    setProfile(null);
    setDisplayName('');
    setProfileLoading(true);
    setError('');
    supabase
      .from('profiles')
      .select('display_name')
      .eq('id', user.id)
      .maybeSingle()
      .then(({ data, error: profileError }) => {
        if (!active) return;
        setProfile(data);
        if (profileError) setError(profileError.message);
        else if (!data) setError('Profile not found. Apply the LINVIA profile migration in Supabase.');
        setProfileLoading(false);
      });

    return () => { active = false; };
  }, [user]);

  async function submitAuth() {
    if (!supabase) return;
    setError('');
    setMessage('');

    const normalizedEmail = email.trim().toLowerCase();
    if (!normalizedEmail || !password) {
      setError('Enter your email and password to continue.');
      return;
    }
    if (mode === 'sign-up' && !displayName.trim()) {
      setError('Enter a display name for your profile.');
      return;
    }

    setBusy(true);
    try {
      if (mode === 'sign-up') {
        const { data, error: authError } = await supabase.auth.signUp({
          email: normalizedEmail,
          password,
          options: { data: { display_name: displayName.trim() } },
        });
        if (authError) throw authError;
        if (!data.session) setMessage('Check your email to confirm your account, then sign in.');
      } else {
        const { error: authError } = await supabase.auth.signInWithPassword({
          email: normalizedEmail,
          password,
        });
        if (authError) throw authError;
      }
    } catch (authError) {
      setError(authError instanceof Error ? authError.message : 'Unable to complete sign in. Try again.');
    } finally {
      setBusy(false);
    }
  }

  async function saveProfile() {
    if (!supabase || !user) return;
    const nextName = displayName.trim();
    if (!nextName || nextName.length > 60) {
      setError('Display name must be between 1 and 60 characters.');
      return;
    }

    setBusy(true);
    setError('');
    setMessage('');
    try {
      const { data, error: updateError } = await supabase
        .from('profiles')
        .update({ display_name: nextName })
        .eq('id', user.id)
        .select('display_name')
        .single();
      if (updateError) setError(updateError.message);
      else {
        setProfile(data);
        setDisplayName(data.display_name);
        setMessage('Profile saved.');
      }
    } catch (saveError) {
      setError(saveError instanceof Error ? saveError.message : 'Unable to save your profile. Try again.');
    } finally {
      setBusy(false);
    }
  }

  async function signOut() {
    if (!supabase) return;
    setBusy(true);
    setError('');
    try {
      const { error: signOutError } = await supabase.auth.signOut();
      if (signOutError) setError(signOutError.message);
    } catch (signOutError) {
      setError(signOutError instanceof Error ? signOutError.message : 'Unable to sign out. Try again.');
    } finally {
      setBusy(false);
    }
  }

  if (loading) {
    return <CenteredState label="Checking your account…" />;
  }

  if (!isSupabaseConfigured) {
    return (
      <ScrollView style={styles.screen} contentContainerStyle={styles.content}>
        <Text style={styles.kicker}>YOUR LINVIA ACCOUNT</Text>
        <Text style={styles.title}>Account</Text>
        <View style={styles.notice}>
          <MaterialCommunityIcons name="cloud-outline" size={25} color={colors.accent} />
          <Text style={styles.noticeTitle}>Connect Supabase to continue</Text>
          <Text style={styles.body}>Add the project URL and publishable key to your local .env file, then restart Expo. Account setup instructions are in the README.</Text>
        </View>
      </ScrollView>
    );
  }

  if (user) {
    return <ProfileView
      user={user}
      profile={profile}
      profileLoading={profileLoading}
      displayName={displayName}
      setDisplayName={setDisplayName}
      busy={busy}
      message={message}
      error={error}
      onSave={saveProfile}
      onSignOut={signOut}
    />;
  }

  return (
    <ScrollView style={styles.screen} contentContainerStyle={styles.content} keyboardShouldPersistTaps="handled">
      <Text style={styles.kicker}>YOUR LINVIA ACCOUNT</Text>
      <Text style={styles.title}>{mode === 'sign-in' ? 'Welcome back.' : 'Join LINVIA.'}</Text>
      <Text style={styles.body}>{mode === 'sign-in' ? 'Sign in to manage your profile and keep your account connected.' : 'Create an account to set up your LINVIA profile.'}</Text>

      <View style={styles.form}>
        {mode === 'sign-up' && <Field label="Display name" value={displayName} onChangeText={setDisplayName} autoCapitalize="words" />}
        <Field label="Email" value={email} onChangeText={setEmail} autoCapitalize="none" keyboardType="email-address" />
        <Field label="Password" value={password} onChangeText={setPassword} secureTextEntry />
        <Feedback error={error} message={message} />
        <PrimaryButton label={mode === 'sign-in' ? 'Sign in' : 'Create account'} busy={busy} onPress={submitAuth} />
      </View>

      <Pressable
        accessibilityRole="button"
        onPress={() => { setMode(mode === 'sign-in' ? 'sign-up' : 'sign-in'); setError(''); setMessage(''); }}
        style={styles.modeSwitch}
      >
        <Text style={styles.modeText}>{mode === 'sign-in' ? 'New to LINVIA? Create an account' : 'Already have an account? Sign in'}</Text>
      </Pressable>
    </ScrollView>
  );
}

function ProfileView({
  user, profile, profileLoading, displayName, setDisplayName, busy, message, error, onSave, onSignOut,
}: {
  user: User;
  profile: Profile | null;
  profileLoading: boolean;
  displayName: string;
  setDisplayName: (value: string) => void;
  busy: boolean;
  message: string;
  error: string;
  onSave: () => void;
  onSignOut: () => void;
}) {
  useEffect(() => {
    if (profile) setDisplayName(profile.display_name);
  }, [profile, setDisplayName]);

  return (
    <ScrollView style={styles.screen} contentContainerStyle={styles.content} keyboardShouldPersistTaps="handled">
      <Text style={styles.kicker}>YOUR LINVIA ACCOUNT</Text>
      <Text style={styles.title}>Your profile</Text>
      <View style={styles.identityCard}>
        <View style={styles.avatar}><MaterialCommunityIcons name="account-outline" size={29} color={colors.accent} /></View>
        <View style={styles.identityText}>
          <Text numberOfLines={1} style={styles.identityName}>{profile?.display_name ?? 'LINVIA listener'}</Text>
          <Text numberOfLines={1} style={styles.identityEmail}>{user.email ?? 'Email unavailable'}</Text>
        </View>
      </View>

      {profileLoading ? <ActivityIndicator color={colors.accent} style={styles.profileLoader} /> : (
        <View style={styles.form}>
          <Field label="Display name" value={displayName} onChangeText={setDisplayName} autoCapitalize="words" />
          <Feedback error={error} message={message} />
          <PrimaryButton label="Save profile" busy={busy} onPress={onSave} />
        </View>
      )}

      <Pressable accessibilityRole="button" disabled={busy} onPress={onSignOut} style={styles.signOutButton}>
        <MaterialCommunityIcons name="logout" size={18} color={colors.secondaryText} />
        <Text style={styles.signOutText}>Sign out</Text>
      </Pressable>
    </ScrollView>
  );
}

function Field({ label, ...inputProps }: {
  label: string;
  value: string;
  onChangeText: (value: string) => void;
  autoCapitalize?: 'none' | 'sentences' | 'words' | 'characters';
  keyboardType?: 'default' | 'email-address';
  secureTextEntry?: boolean;
}) {
  return (
    <View style={styles.field}>
      <Text style={styles.fieldLabel}>{label}</Text>
      <TextInput
        accessibilityLabel={label}
        autoCapitalize={inputProps.autoCapitalize ?? 'none'}
        autoCorrect={false}
        keyboardType={inputProps.keyboardType ?? 'default'}
        onChangeText={inputProps.onChangeText}
        placeholder={label}
        placeholderTextColor={colors.muted}
        secureTextEntry={inputProps.secureTextEntry}
        style={styles.input}
        value={inputProps.value}
      />
    </View>
  );
}

function PrimaryButton({ label, busy, onPress }: { label: string; busy: boolean; onPress: () => void }) {
  return (
    <Pressable accessibilityRole="button" disabled={busy} onPress={onPress} style={[styles.primaryButton, busy && styles.disabledButton]}>
      {busy ? <ActivityIndicator color={colors.background} /> : <Text style={styles.primaryButtonText}>{label}</Text>}
    </Pressable>
  );
}

function Feedback({ error, message }: { error: string; message: string }) {
  if (!error && !message) return null;
  return <Text accessibilityRole="alert" style={[styles.feedback, error ? styles.errorText : styles.successText]}>{error || message}</Text>;
}

function CenteredState({ label }: { label: string }) {
  return <View style={styles.centered}><ActivityIndicator color={colors.accent} /><Text style={styles.body}>{label}</Text></View>;
}

const styles = StyleSheet.create({
  screen: { flex: 1, backgroundColor: colors.background },
  content: { paddingHorizontal: spacing.page, paddingTop: 26, paddingBottom: 40 },
  kicker: { color: colors.silver, fontSize: 10, fontWeight: '800', letterSpacing: 1.5 },
  title: { color: colors.text, fontSize: 30, fontWeight: '800', marginTop: 8 },
  body: { color: colors.secondaryText, fontSize: 13, lineHeight: 20, marginTop: 10 },
  form: { gap: 15, marginTop: 25 },
  field: { gap: 7 },
  fieldLabel: { color: colors.secondaryText, fontSize: 12, fontWeight: '600' },
  input: { minHeight: 50, color: colors.text, fontSize: 15, paddingHorizontal: 14, borderRadius: 8, backgroundColor: colors.surface, borderWidth: 1, borderColor: colors.border },
  primaryButton: { minHeight: 50, alignItems: 'center', justifyContent: 'center', borderRadius: 8, backgroundColor: colors.accent, marginTop: 4 },
  primaryButtonText: { color: colors.background, fontSize: 14, fontWeight: '800' },
  disabledButton: { opacity: 0.65 },
  feedback: { fontSize: 12, lineHeight: 18 },
  errorText: { color: '#FF8A8A' },
  successText: { color: '#79D6A0' },
  modeSwitch: { alignItems: 'center', paddingVertical: 18 },
  modeText: { color: colors.silver, fontSize: 12, fontWeight: '600' },
  notice: { alignItems: 'center', padding: 22, borderWidth: 1, borderColor: colors.border, borderRadius: 10, backgroundColor: colors.surface, marginTop: 26 },
  noticeTitle: { color: colors.text, fontSize: 16, fontWeight: '700', marginTop: 14 },
  centered: { flex: 1, alignItems: 'center', justifyContent: 'center', gap: 10, backgroundColor: colors.background },
  identityCard: { flexDirection: 'row', alignItems: 'center', gap: 13, padding: 16, backgroundColor: colors.surface, borderRadius: 9, borderWidth: 1, borderColor: colors.border, marginTop: 24 },
  avatar: { width: 52, height: 52, borderRadius: 26, alignItems: 'center', justifyContent: 'center', backgroundColor: colors.elevated },
  identityText: { flex: 1, gap: 5 },
  identityName: { color: colors.text, fontSize: 15, fontWeight: '700' },
  identityEmail: { color: colors.secondaryText, fontSize: 12 },
  profileLoader: { marginTop: 35 },
  signOutButton: { flexDirection: 'row', alignItems: 'center', justifyContent: 'center', gap: 8, paddingVertical: 14, marginTop: 25 },
  signOutText: { color: colors.secondaryText, fontSize: 13, fontWeight: '600' },
});
