import { StyleSheet, Text, View } from 'react-native';
import type { Track } from '../../features/catalog/types';

type ArtworkProps = Pick<Track, 'title' | 'artworkColor'> & { size?: number };

export function Artwork({ title, artworkColor, size = 52 }: ArtworkProps) {
  return (
    <View style={[styles.artwork, { width: size, height: size, backgroundColor: artworkColor }]}>
      <Text numberOfLines={1} style={[styles.letter, { fontSize: size * 0.48 }]}>{title.slice(0, 1)}</Text>
      <View style={styles.rule} />
    </View>
  );
}

const styles = StyleSheet.create({
  artwork: { borderRadius: 5, alignItems: 'center', justifyContent: 'center', overflow: 'hidden' },
  letter: { color: '#FFFFFF', fontWeight: '800', opacity: 0.92 },
  rule: { position: 'absolute', width: '150%', height: 1, bottom: 10, backgroundColor: 'rgba(255,255,255,0.42)', transform: [{ rotate: '-35deg' }] },
});