# LINVIA - Advanced Music Streaming Platform

A comprehensive music streaming ecosystem named **LINVIA** with a web UI clone and a full-featured Android app that goes beyond Spotify with AI recommendations, social listening, podcasts, audiobooks, music videos, concert tickets, music recognition, and collaborative playlists.

---

## 📱 LINVIA Android App (in `/android/`)

A feature-rich Android music streaming app built with Kotlin and Jetpack Compose, designed to go beyond Spotify with advanced AI-powered features.

### 🚀 Advanced Features Beyond Spotify

| Feature | Description |
|---------|-------------|
| **🧠 AI Recommendations** | Mood/context-based playlists (Focus, Workout, Chill, Party, Sleep, Discovery, Mood Booster, Throwback) |
| **👥 Social Listening Rooms** | Real-time group listening with room codes, active room browser, chat |
| **🎯 Smart Offline Mode** | AI-predicted auto-downloads, smart cache management |
| **🎧 Enhanced Audio** | Built-in equalizer, crossfade, gapless playback, sleep timer |
| **📻 Podcasts** | Subscribe to podcasts, download episodes, track progress |
| **📚 Audiobooks** | Purchase and listen to audiobooks with chapter support |
| **🎬 Music Videos** | Watch official music videos, trending and featured |
| **🎤 Music Recognition** | Shazam-like song identification |
| **📊 Music Charts** | Global, US, UK, and regional top 50 charts |
| **🎫 Concert Tickets** | Browse upcoming concerts and buy tickets |
| **🤝 Collaborative Playlists** | Create playlists friends can contribute to |
| **🎯 Smart Playlists** | Auto-generated based on mood, history, preferences |

### 📱 Android Screens (18 total)

| Screen | Description |
|--------|-------------|
| Choose Mode | Dark/Light theme selector |
| Sign In | Authentication with email/password |
| Register | Account creation |
| Home | Personalized feed with Quick Access grid |
| Search | Track/artist/album search |
| Library | Your music collection |
| Profile | User profile & settings |
| Now Playing | Full player with controls |
| Lyrics | Synced lyrics display |
| Social Listening | Real-time group listening rooms |
| AI Recommendations | AI-curated playlists by mood |
| Podcasts | Subscribe, download, listen |
| Audiobooks | Purchase and listen |
| Music Videos | Official videos, trending |
| Music Recognition | Identify songs instantly |
| Music Charts | Global & regional charts |
| Collaborative Playlists | Shared playlists with friends |
| Concerts | Browse and buy tickets |

### 🛠️ Android Tech Stack

- **100% Kotlin** with Jetpack Compose UI
- **Material3** design system
- **Hilt** dependency injection
- **Room** database for offline storage
- **ExoPlayer** for media playback
- **Coroutines + Flow** for reactive programming
- **Retrofit + OkHttp** for API
- **Coil** for image loading
- **Foreground service** for background playback

### 🛠️ Android Setup & Build

```bash
# Navigate to Android project
cd android

# Make gradlew executable
chmod +x gradlew

# Build the project
./gradlew build

# Run on device/emulator
./gradlew installDebug
```

### 📋 Android Requirements

- Android Studio Giraffe (2022.3.1) or later
- JDK 11 or later
- Android SDK 34 (compileSdk)
- Minimum SDK 24 (Android 7.0)

---

## 🌐 LINVIA Web Version (root)

A reverse-engineered, pixel-perfect Spotify UI clone with:

### Screens Included

- **Onboarding** — "Get Started" welcome screen
- **Choose Mode** — Dark/Light theme selector
- **Home** — News tab, Made for You, Playlists
- **Sign In** — Username/email + password with Google & Apple OAuth
- **Register** — Full name, email, password with Google & Apple OAuth
- **Now Playing** — Full player with progress bar
- **Lyrics** — Synced lyrics with verse markers
- **Library** — Recently added, Playlists, Artists, Albums (light theme)
- **Search** — Artist and track search
- **Profile** — User profile with library

### Tech Stack (Web)

- HTML5
- CSS3 (CSS variables, Flexbox, Grid, responsive design)
- Font Awesome 6 (icons)
- Vanilla JavaScript (minimal, for theme switching)

---

## 🛠️ Getting Started (Web)

```bash
# Clone the repository
git clone https://github.com/Young-zeroCipher/Linvia.git

# Open in browser
cd Linvia
open index.html
```

---

## 📄 License

MIT License — feel free to use this as a learning resource or template for your own music streaming UI.

---

**LINVIA** — Play. Download. Enjoy. Smarter.
