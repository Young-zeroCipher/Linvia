import { MaterialCommunityIcons } from '@expo/vector-icons';
import { router } from 'expo-router';
import { Pressable, ScrollView, StyleSheet, Text, View } from 'react-native';
import { developmentCatalog } from '../../features/catalog/data/developmentCatalog';
import { useFavoritesStore } from '../../features/library/store/favorites';
import { TrackRow } from '../../shared/ui/TrackRow';
import { colors } from '../../shared/theme/colors';
import { spacing } from '../../shared/theme/spacing';

export default function LibraryScreen() {
  const favoriteIds = useFavoritesStore((state) => state.trackIds);
  const favorites = developmentCatalog.filter((track) => favoriteIds.includes(track.id));

  return (
    <ScrollView style={styles.screen} contentContainerStyle={styles.content}>
      <Text style={styles.kicker}>YOUR SPACE</Text>
      <Text style={styles.title}>Your library</Text>
      <Text style={styles.subtitle}>Saved tracks · {favorites.length}</Text>
      {favorites.length > 0 ? favorites.map((track) => <TrackRow key={track.id} track={track} />) : (
        <View style={styles.empty}>
          <View style={styles.emptyIcon}><MaterialCommunityIcons name="bookmark-music-outline" size={28} color={colors.accent} /></View>
          <Text style={styles.emptyTitle}>Your collection starts here.</Text>
          <Text style={styles.emptyText}>Save a track to keep it close.</Text>
          <Pressable accessibilityRole="button" onPress={() => router.push('/search')} style={styles.browseButton}>
            <Text style={styles.browseText}>Explore music</Text>
            <MaterialCommunityIcons name="arrow-right" size={17} color={colors.text} />
          </Pressable>
        </View>
      )}
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  screen: { flex: 1, backgroundColor: colors.background },
  content: { paddingHorizontal: spacing.page, paddingTop: 26, paddingBottom: 32 },
  kicker: { color: colors.silver, fontSize: 10, fontWeight: '800', letterSpacing: 1.5 },
  title: { color: colors.text, fontSize: 30, fontWeight: '800', marginTop: 8 },
  subtitle: { color: colors.secondaryText, fontSize: 13, marginTop: 8, marginBottom: 18 },
  empty: { alignItems: 'center', paddingTop: 80 },
  emptyIcon: { width: 58, height: 58, borderRadius: 29, backgroundColor: colors.surface, alignItems: 'center', justifyContent: 'center' },
  emptyTitle: { color: colors.text, fontSize: 17, fontWeight: '700', marginTop: 18 },
  emptyText: { color: colors.secondaryText, fontSize: 13, marginTop: 7 },
  browseButton: { flexDirection: 'row', alignItems: 'center', gap: 9, paddingHorizontal: 15, paddingVertical: 12, borderRadius: 7, backgroundColor: colors.accent, marginTop: 22 },
  browseText: { color: colors.text, fontSize: 13, fontWeight: '700' },
});