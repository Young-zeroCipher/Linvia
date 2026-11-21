# LINVIA - Spotify Clone

A reverse-engineered, pixel-perfect Spotify UI clone named **LINVIA** with a dark/light mode toggle, authentication screens, home feed, music player with lyrics, library, search, and profile pages.

## Screens Included

- **Onboarding** — "Get Started" welcome screen
- **Choose Mode** — Dark/Light theme selector
- **Home** — News tab, Made for You, Playlists, Now Playing bar
- **Sign In** — Username/email + password with Google & Apple OAuth
- **Register** — Full name, email, password with Google & Apple OAuth
- **Now Playing** — Full player with progress bar, shuffle, repeat, playback controls
- **Lyrics** — Synced lyrics with verse markers
- **Library** — Recently added, Playlists, Artists, Albums (light theme variant)
- **Search** — Artist and track search
- **Profile** — User profile with library

## Tech Stack

- HTML5
- CSS3 (CSS variables, Flexbox, Grid, responsive design)
- Font Awesome 6 (icons)
- Vanilla JavaScript (minimal, for theme switching)
- No build step — open `index.html` directly in any browser

## Design

LINVIA uses Spotify's signature green (`#1db954`) as the accent color with a dark theme (`#121212`) by default, plus a full light mode variant. The layout is mobile-first with a bottom navigation bar and fixed now-playing bar, mirroring the Spotify mobile app experience.

## Getting Started

```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/linvia.git

# Open in browser
cd linity
open index.html
```

## Project Structure

```
linvia/
├── index.html          # Onboarding / Welcome
├── choose-mode.html    # Dark/Light mode selector
├── home.html           # Home feed
├── signin.html         # Sign in page
├── register.html       # Register page
├── now-playing.html    # Music player
├── lyrics.html         # Synced lyrics
├── library.html        # Library (light theme)
├── search.html         # Search page
├── profile.html        # User profile
├── css/
│   └── style.css       # Main stylesheet
└── README.md
```

## License

MIT License — feel free to use this as a learning resource or template for your own music streaming UI.

---

**LINVIA** — Play. Download. Enjoy.
