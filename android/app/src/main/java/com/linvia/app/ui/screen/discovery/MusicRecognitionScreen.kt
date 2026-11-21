package com.linvia.app.ui.screen.discovery

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.linvia.app.ui.theme.LinviaGreen

@Composable
fun MusicRecognitionScreen(navController: NavHostController) {
    var isListening by remember { mutableStateOf(false) }
    var recognizedTrack by remember { mutableStateOf<String?>(null) }
    var recognizedArtist by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Music Recognition") }) },
        contentPadding = PaddingValues(24.dp)
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (recognizedTrack != null) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Recognized!", style = MaterialTheme.typography.headlineLarge, color = LinviaGreen)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = recognizedTrack, style = MaterialTheme.typography.headlineMedium)
                    Text(text = recognizedArtist ?: "Unknown Artist", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = { recognizedTrack = null; recognizedArtist = null }) {
                        Text("Scan Again")
                    }
                }
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = if (isListening) Icons.Default.Mic else Icons.Default.MicNone,
                        contentDescription = null,
                        tint = if (isListening) LinviaGreen else MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(64.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = if (isListening) "Listening..." else "Tap to identify music",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    if (isListening) {
                        Text(text = "Hold your phone near the music source", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = { isListening = true },
                        modifier = Modifier.size(56.dp).background(
                            if (isListening) MaterialTheme.colorScheme.onSurfaceVariant else LinviaGreen,
                            CircleShape
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Stop,
                            contentDescription = null,
                            tint = if (isListening) LinviaGreen else androidx.compose.ui.graphics.Color.Black
                        )
                    }
                }
            }
        }
    }
}
