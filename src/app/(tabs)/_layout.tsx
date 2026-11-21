import { MaterialCommunityIcons } from '@expo/vector-icons';
import { Tabs } from 'expo-router';
import { colors } from '../../shared/theme/colors';

export default function TabLayout() {
  return (
    <Tabs
      screenOptions={{
        headerShown: false,
        tabBarActiveTintColor: colors.accent,
        tabBarInactiveTintColor: colors.muted,
        tabBarStyle: {
          backgroundColor: colors.surface,
          borderTopColor: colors.border,
          height: 66,
          paddingTop: 8,
          paddingBottom: 8,
        },
        tabBarLabelStyle: { fontSize: 11, fontWeight: '600' },
      }}
    >
      <Tabs.Screen
        name="index"
        options={{
          title: 'Discover',
          tabBarIcon: ({ color, size }) => <MaterialCommunityIcons name="compass-outline" color={typeof color === 'string' ? color : colors.muted} size={size} />,
        }}
      />
      <Tabs.Screen
        name="search"
        options={{
          title: 'Search',
          tabBarIcon: ({ color, size }) => <MaterialCommunityIcons name="magnify" color={typeof color === 'string' ? color : colors.muted} size={size} />,
        }}
      />
      <Tabs.Screen
        name="library"
        options={{
          title: 'Your library',
          tabBarIcon: ({ color, size }) => <MaterialCommunityIcons name="bookmark-music-outline" color={typeof color === 'string' ? color : colors.muted} size={size} />,
        }}
      />
      <Tabs.Screen
        name="account"
        options={{
          title: 'Account',
          tabBarIcon: ({ color, size }) => <MaterialCommunityIcons name="account-circle-outline" color={typeof color === 'string' ? color : colors.muted} size={size} />,
        }}
      />
    </Tabs>
  );
}
