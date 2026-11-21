package com.linvia.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun LinviaApp() {
    Surface(
        modifier = fillMaxSize(),
        color = androidx.compose.material3.MaterialTheme.colorScheme.background
    ) {
        LinviaNavHost()
    }
}
