package com.linvia.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.linvia.app.model.Track
import com.linvia.app.ui.theme.LinviaGreen
import coil.compose.rememberImagePainter
import com.linvia.app.viewmodel.PlayerViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NowPlayingScreen(navController: NavHostController, viewModel: PlayerViewModel = hiltViewModel()) {
    val track = Track("1", "Bad Guy", "Billie Eilish", "When We All Fall Asleep", "https://picsum.photos/300", null, 194000, "Pop", 2019)

    Scaffold(
        topBar = { TopAppBar(title = { Text("Now Playing") }) },
        contentPadding = PaddingValues(24.dp)
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                painter = rememberImagePainter(request = track.albumArtUrl ?: ""),
                contentDescription = null,
                modifier = Modifier.size(280.dp).clip(RoundedCornerShape(24.dp))
            )
            Text(text = track.title, style = MaterialTheme.typography.headlineLarge)
            Text(text = track.artist, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Slider(
                value = 50f,
                onValueChange = {},
                valueRange = 0f..100f,
                modifier = Modifier.fillMaxWidth(0.8f)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) { Icon(Icons.Default.SkipPrevious, contentDescription = null) }
                IconButton(
                    onClick = { viewModel.play() },
                    modifier = Modifier.size(56.dp).background(LinviaGreen, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Pause,
                        contentDescription = null,
                        tint = androidx.compose.ui.graphics.Color.Black
                    )
                }
                IconButton(onClick = {}) { Icon(Icons.Default.SkipNext, contentDescription = null) }
            }
        }
    }
}
