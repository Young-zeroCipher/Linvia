import { MaterialCommunityIcons } from '@expo/vector-icons';
import { router } from 'expo-router';
import { Pressable, ScrollView, StyleSheet, Text, View } from 'react-native';
import { developmentCatalog, discoveryStyles } from '../../features/catalog/data/developmentCatalog';
import { TrackRow } from '../../shared/ui/TrackRow';
import { colors } from '../../shared/theme/colors';
import { spacing } from '../../shared/theme/spacing';

export default function DiscoverScreen() {
  return (
    <ScrollView style={styles.screen} contentContainerStyle={styles.content}>
      <View style={styles.header}>
        <View>
          <Text style={styles.brand}>LINVIA</Text>
          <Text style={styles.signature}>POWERED BY YOUNG NDABA GROUP</Text>
        </View>
        <Pressable
          accessibilityRole="button"
          accessibilityLabel="Search music"
          onPress={() => router.push('/search')}
          style={styles.searchButton}
        >
          <MaterialCommunityIcons name="magnify" size={23} color={colors.text} />
        </Pressable>
      </View>

      <View style={styles.intro}>
        <Text style={styles.eyebrow}>DEVELOPMENT CATALOG</Text>
        <Text style={styles.title}>Find your{ '\n' }next favorite.</Text>
        <Text style={styles.description}>A first look at independent sounds, curated for you.</Text>
      </View>

      <View style={styles.section}>
        <Text style={styles.sectionTitle}>Explore a mood</Text>
        <ScrollView horizontal showsHorizontalScrollIndicator={false} contentContainerStyle={styles.moods}>
          {discoveryStyles.map((style) => (
            <Pressable
              key={style}
              accessibilityRole="button"
              onPress={() => router.push({ pathname: '/search', params: { q: style } })}
              style={styles.moodButton}
            >
              <Text style={styles.moodText}>{style}</Text>
              <MaterialCommunityIcons name="arrow-top-right" size={16} color={colors.accent} />
            </Pressable>
          ))}
        </ScrollView>
      </View>

      <View style={styles.section}>
        <View style={styles.sectionHeading}>
          <Text style={styles.sectionTitle}>A starting point</Text>
          <Text style={styles.sampleLabel}>SAMPLE TRACKS</Text>
        </View>
        {developmentCatalog.slice(0, 5).map((track) => <TrackRow key={track.id} track={track} />)}
      </View>
      <Text style={styles.footer}>LINVIA · BUILT FOR THE SOUND AHEAD</Text>
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  screen: { flex: 1, backgroundColor: colors.background },
  content: { paddingHorizontal: spacing.page, paddingTop: 18, paddingBottom: 32 },
  header: { flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between' },
  brand: { color: colors.text, fontSize: 22, fontWeight: '800', letterSpacing: 2 },
  signature: { color: colors.silver, fontSize: 8, fontWeight: '700', letterSpacing: 1.1, marginTop: 3 },
  searchButton: { width: 44, height: 44, borderRadius: 22, backgroundColor: colors.surface, alignItems: 'center', justifyContent: 'center' },
  intro: { paddingTop: 38, paddingBottom: 26 },
  eyebrow: { color: colors.accent, fontSize: 10, fontWeight: '800', letterSpacing: 1.6 },
  title: { color: colors.text, fontSize: 38, fontWeight: '800', lineHeight: 43, marginTop: 12 },
  description: { color: colors.secondaryText, fontSize: 14, lineHeight: 21, marginTop: 10, maxWidth: 290 },
  section: { marginTop: 20 },
  sectionTitle: { color: colors.text, fontSize: 17, fontWeight: '700' },
  moods: { gap: 9, paddingTop: 13 },
  moodButton: { flexDirection: 'row', alignItems: 'center', gap: 12, paddingVertical: 11, paddingHorizontal: 13, borderRadius: 7, backgroundColor: colors.surface, borderWidth: 1, borderColor: colors.border },
  moodText: { color: colors.text, fontSize: 13, fontWeight: '600' },
  sectionHeading: { flexDirection: 'row', alignItems: 'center', justifyContent: 'space-between', marginBottom: 5 },
  sampleLabel: { color: colors.muted, fontSize: 9, fontWeight: '700', letterSpacing: 1 },
  footer: { color: colors.muted, fontSize: 9, fontWeight: '700', letterSpacing: 1.2, textAlign: 'center', marginTop: 28 },
});