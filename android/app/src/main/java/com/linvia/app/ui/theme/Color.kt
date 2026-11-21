package com.linvia.app.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

val LinviaGreen = Color(0xFF1DB954)
val LinviaDark = Color(0xFF121212)
val LinviaCardDark = Color(0xFF181818)
val LinviaLight = Color(0xFFFFFFFF)
val LinviaCardLight = Color(0xFFF5F5F5)

fun LinviaColorScheme(darkTheme: Boolean = true): ColorScheme {
    return if (darkTheme) {
        ColorScheme.dark(
            primary = LinviaGreen,
            secondary = Color(0xFF1ED760),
            background = LinviaDark,
            surface = LinviaCardDark,
            onPrimary = Color(0xFF000000),
            onSecondary = Color(0xFF000000),
            onBackground = LinviaLight,
            onSurface = LinviaLight
        )
    } else {
        ColorScheme.light(
            primary = LinviaGreen,
            secondary = Color(0xFF1ED760),
            background = LinviaLight,
            surface = LinviaCardLight,
            onPrimary = Color(0xFF000000),
            onSecondary = Color(0xFF000000),
            onBackground = Color(0xFF000000),
            onSurface = Color(0xFF000000)
        )
    }
}
