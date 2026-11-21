# LINVIA - Advanced Android Music App

A feature-rich Android music streaming app built with Kotlin and Jetpack Compose, designed to go beyond Spotify with advanced AI-powered features.

## 🚀 Advanced Features Beyond Spotify

### 1. 🧠 AI-Powered Recommendations
- Mood-based music suggestions
- Listening history analysis
- Discovery playlists curated by AI
- Context-aware recommendations (workout, focus, sleep, party)

### 2. 👥 Social Listening Rooms
- Create and join real-time listening rooms
- Sync playback with friends globally
- Chat while listening together
- Room codes for easy joining

### 3. 🎯 Smart Offline Mode
- AI predicts tracks you'll listen to
- Auto-downloads for offline listening
- Smart cache management
- Data usage optimization

### 4. 🎧 Enhanced Audio Features
- Built-in equalizer with presets
- Crossfade between tracks
- Gapless playback
- Sleep timer
- Lyrics translation (multi-language)

### 5. 📱 Modern Android Architecture
- 100% Kotlin
- Jetpack Compose UI
- Hilt dependency injection
- Room database for offline storage
- ExoPlayer for media playback
- Coroutines + Flow for reactive programming

## 🏗️ Project Structure

```
linvia-android/
├── app/
│   ├── src/main/
│   │   ├── java/com/linvia/app/
│   │   │   ├── di/              # Hilt dependency injection
│   │   │   ├── model/           # Data models (Track, Album, Artist, Playlist, User)
│   │   │   ├── repository/      # Repository pattern
│   │   │   ├── service/         # API service & Music playback service
│   │   │   ├── ui/              # Compose UI
│   │   │   │   ├── theme/       # Material3 theme & colors
│   │   │   │   ├── screen/      # All app screens
│   │   │   │   ├── Navigation.kt
│   │   │   │   ├── MainActivity.kt
│   │   │   │   └── LinviaApp.kt
│   │   │   └── util/            # Utilities
│   │   │   └── viewmodel/       # ViewModels with Hilt
│   │   ├── res/
│   │   │   ├── drawable/
│   │   │   ├── values/
│   │   │   ├── xml/
│   │   │   └── ...
│   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── README.md
```

## 📋 Screens Included

| Screen | Description |
|--------|-------------|
| Choose Mode | Dark/Light theme selector |
| Sign In | Authentication with email/password |
| Register | Account creation |
| Home | Personalized feed with Made for You |
| Search | Track/artist/album search |
| Library | Your music collection |
| Profile | User profile & settings |
| Now Playing | Full player with controls |
| Lyrics | Synced lyrics display |
| Social Listening | Real-time group listening |
| AI Recommendations | AI-curated playlists |

## 🛠️ Setup & Build

```bash
# Clone the repository
git clone https://github.com/Young-zeroCipher/Linvia-Android.git

# Navigate to project
cd Linvia-Android

# Make gradlew executable
chmod +x gradlew

# Build the project
./gradlew build

# Run on device/emulator
./gradlew installDebug
```

## 🔧 Requirements

- Android Studio Giraffe (2022.3.1) or later
- JDK 11 or later
- Android SDK 34 (compileSdk)
- Minimum SDK 24 (Android 7.0)

## 📄 License

MIT License

---

**LINVIA** - Play. Download. Enjoy. Smarter.
