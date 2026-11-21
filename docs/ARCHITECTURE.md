# Architecture

## Current app foundation

LINVIA is an Android-first Expo app written in TypeScript. Expo Router owns navigation under `src/app`; each route composes feature logic and reusable UI rather than owning catalog or state rules. `src/features` groups business capabilities, and `src/shared` contains cross-feature presentation tokens and components.

The initial catalog and search are local development fixtures. Search is a pure function in `features/catalog`, and favorites live in a small in-memory Zustand store. TanStack Query is configured at the app root for future server state. Supabase email/password authentication and profile editing are implemented in `features/auth`; sessions are encrypted for device storage, and `supabase/migrations` defines an owner-scoped profile table with RLS. The catalog remains local, and no audio behavior is implemented.

## Planned boundaries

- `features/auth`: Supabase Auth client integration and profile workflows (implemented)
- `features/player`: playback controller interface, queue, and player state; begin with Expo Audio and isolate the implementation so Android Media3 can replace it later
- `features/catalog`: authorized artist, album, and track metadata; server-mediated playback authorization
- `features/library`: playlists, favorites, and listening history
- `features/uploads`: artist registration and upload workflow backed by signed, short-lived server authorization
- `features/moderation`: rights review, moderation actions, and audit history
- `server`: Cloudflare Workers for privileged operations, rate limits, signed R2 access, and authorization checks
- `supabase`: PostgreSQL migrations, RLS policies, and non-privileged client configuration

## Security constraints

Supabase service-role credentials, R2 secrets, and signing keys belong only in server-side secret storage. Mobile clients receive only public configuration and narrowly scoped, short-lived authorizations. Private audio must not have a public bucket URL. RLS is required for user-owned data, and administrative or rights-sensitive actions must be authorized and audited server-side.

## Next module

Before release, connect a real Supabase project, apply the profile migration, and verify account creation and profile access on Android. Keep uploads and private audio behind server-side authorization. Playback should follow with an explicit authorized test asset and a modular Expo Audio controller.
