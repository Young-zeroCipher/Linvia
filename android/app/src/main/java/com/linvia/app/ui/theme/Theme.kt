package com.linvia.app.ui.theme

import androidx.compose.foundation.isSystemInLightTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.compose.material3.TextStyle
import androidx.compose.ui.text.FontWeight

private val DarkColorScheme = dynamicDarkColorScheme()
private val LightColorScheme = dynamicLightColorScheme()

@Composable
fun LinviaTheme(
    darkTheme: Boolean = isSystemInLightTheme().not(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = androidx.compose.material3.Typography(
            body1 = TextStyle(fontSize = 16.sp),
            headlineMedium = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
            titleLarge = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
            titleMedium = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
            labelLarge = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium)
        ),
        content = content
    )
}
