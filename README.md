# LINVIA

An independent Android-first Expo app by **YOUNG NDABA GROUP**. LINVIA is built around legally authorized music and its own catalog; it does not depend on third-party private APIs, protected streams, or DRM circumvention.

## Current stage

LINVIA is an Android-first Expo music app foundation with file-based tab navigation, a dark theme, reusable track UI, local catalog search, and an in-memory saved-tracks library. It now includes Supabase email/password authentication and an owner-scoped profile flow. The six fictional tracks are development fixtures, not a licensed catalog. No audio is played or streamed yet.

## Requirements

- Node.js 20.19 or newer and npm
- Android Studio with an Android SDK/emulator, or an Android device with Expo Go
- JDK supported by the installed Android Gradle tooling for native builds

## Run

```powershell
npm install
npm run start
```

Press `a` in the Expo terminal to open an available Android emulator, or scan the development QR code with Expo Go. For a native Android build, run `npx expo run:android` after installing the Android SDK and configuring the local environment.

## Supabase account setup

1. Create a Supabase project and copy `.env.example` to `.env`.
2. Set `EXPO_PUBLIC_SUPABASE_URL` and `EXPO_PUBLIC_SUPABASE_PUBLISHABLE_KEY` from the Supabase project's Connect panel.
3. Run `supabase/migrations/202611210001_profiles.sql` in the Supabase SQL Editor.
4. Restart Expo after changing `.env` so the app loads the new public configuration.

The Account tab supports email/password sign-up, sign-in, profile display-name updates, and sign-out. If email confirmation is enabled in Supabase Auth, new users must confirm their address before signing in. Only the publishable key belongs in the mobile app; never put a service-role key in an `EXPO_PUBLIC_` variable.

## Checks

```powershell
npm run typecheck
npm test
```

## Project map

- `src/app`: Expo Router layouts and screens
- `src/features/auth`: Supabase client, encrypted session storage, and auth context
- `src/features/catalog`: track type, development fixtures, and search logic
- `src/features/library`: Zustand favorites state
- `src/shared/theme`: design tokens
- `src/shared/ui`: reusable artwork and track-row UI
- `tests`: focused business-logic tests
- `supabase/migrations`: profile schema and row-level access policies
- `docs/ARCHITECTURE.md`: module boundaries, data flow, and planned backend/security design

## Ownership

Project owner: YOUNG NDABA DEV & YOUNG NDABA GROUP.
