import { MaterialCommunityIcons } from '@expo/vector-icons';
import { Pressable, StyleSheet, Text, View } from 'react-native';
import type { Track } from '../../features/catalog/types';
import { useFavoritesStore } from '../../features/library/store/favorites';
import { colors } from '../theme/colors';
import { spacing } from '../theme/spacing';
import { Artwork } from './Artwork';

type TrackRowProps = { track: Track };

export function TrackRow({ track }: TrackRowProps) {
  const isFavorite = useFavoritesStore((state) => state.trackIds.includes(track.id));
  const toggleTrack = useFavoritesStore((state) => state.toggleTrack);

  return (
    <View style={styles.row}>
      <Artwork title={track.title} artworkColor={track.artworkColor} />
      <View style={styles.details}>
        <Text numberOfLines={1} style={styles.title}>{track.title}</Text>
        <Text numberOfLines={1} style={styles.meta}>{track.artist} · {track.style}</Text>
      </View>
      <Pressable
        accessibilityLabel={isFavorite ? `Remove ${track.title} from saved tracks` : `Save ${track.title}`}
        accessibilityRole="button"
        hitSlop={8}
        onPress={() => toggleTrack(track.id)}
        style={styles.saveButton}
      >
        <MaterialCommunityIcons name={isFavorite ? 'bookmark' : 'bookmark-outline'} size={21} color={isFavorite ? colors.accent : colors.silver} />
      </Pressable>
    </View>
  );
}

const styles = StyleSheet.create({
  row: { minHeight: 68, flexDirection: 'row', alignItems: 'center', gap: spacing.rowGap, borderBottomWidth: StyleSheet.hairlineWidth, borderBottomColor: colors.border },
  details: { flex: 1, minWidth: 0 },
  title: { color: colors.text, fontSize: 14, fontWeight: '600' },
  meta: { color: colors.secondaryText, fontSize: 12, marginTop: 5 },
  saveButton: { width: 38, height: 42, alignItems: 'center', justifyContent: 'center' },
});