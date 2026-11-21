import { MaterialCommunityIcons } from '@expo/vector-icons';
import { useLocalSearchParams } from 'expo-router';
import { useEffect, useState } from 'react';
import { ScrollView, StyleSheet, Text, TextInput, View } from 'react-native';
import { developmentCatalog } from '../../features/catalog/data/developmentCatalog';
import { searchTracks } from '../../features/catalog/searchTracks';
import { TrackRow } from '../../shared/ui/TrackRow';
import { colors } from '../../shared/theme/colors';
import { spacing } from '../../shared/theme/spacing';

export default function SearchScreen() {
  const { q } = useLocalSearchParams<{ q?: string }>();
  const [query, setQuery] = useState(typeof q === 'string' ? q : '');
  useEffect(() => {
    if (typeof q === 'string') setQuery(q);
  }, [q]);
  const results = searchTracks(developmentCatalog, query);

  return (
    <ScrollView style={styles.screen} contentContainerStyle={styles.content} keyboardShouldPersistTaps="handled">
      <Text style={styles.kicker}>LINVIA</Text>
      <Text style={styles.title}>Search</Text>
      <View style={styles.inputWrap}>
        <MaterialCommunityIcons name="magnify" size={21} color={colors.silver} />
        <TextInput
          accessibilityLabel="Search tracks, artists, or styles"
          autoCapitalize="none"
          onChangeText={setQuery}
          placeholder="Tracks, artists, styles"
          placeholderTextColor={colors.muted}
          returnKeyType="search"
          style={styles.input}
          value={query}
        />
        {query.length > 0 && <MaterialCommunityIcons name="close-circle" size={18} color={colors.muted} onPress={() => setQuery('')} />}
      </View>
      <Text style={styles.resultHeading}>{query ? `${results.length} results` : 'Explore the sample catalog'}</Text>
      {results.length > 0 ? results.map((track) => <TrackRow key={track.id} track={track} />) : (
        <View style={styles.empty}>
          <MaterialCommunityIcons name="music-note-off-outline" size={27} color={colors.silver} />
          <Text style={styles.emptyTitle}>No matches yet</Text>
          <Text style={styles.emptyText}>Try a track, artist, or music style.</Text>
        </View>
      )}
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  screen: { flex: 1, backgroundColor: colors.background },
  content: { paddingHorizontal: spacing.page, paddingTop: 26, paddingBottom: 32 },
  kicker: { color: colors.silver, fontSize: 10, fontWeight: '800', letterSpacing: 1.5 },
  title: { color: colors.text, fontSize: 30, fontWeight: '800', marginTop: 8, marginBottom: 18 },
  inputWrap: { height: 52, flexDirection: 'row', alignItems: 'center', gap: 10, paddingHorizontal: 14, borderRadius: 8, backgroundColor: colors.surface, borderWidth: 1, borderColor: colors.border },
  input: { flex: 1, color: colors.text, fontSize: 15, paddingVertical: 0 },
  resultHeading: { color: colors.secondaryText, fontSize: 12, fontWeight: '600', marginTop: 25, marginBottom: 8 },
  empty: { alignItems: 'center', paddingVertical: 50 },
  emptyTitle: { color: colors.text, fontSize: 16, fontWeight: '700', marginTop: 12 },
  emptyText: { color: colors.secondaryText, fontSize: 13, marginTop: 6 },
});