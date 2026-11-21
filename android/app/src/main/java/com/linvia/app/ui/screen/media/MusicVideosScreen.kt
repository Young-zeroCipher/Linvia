package com.linvia.app.ui.screen.media

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.linvia.app.model.media.MusicVideo
import coil.compose.rememberImagePainter
import com.linvia.app.viewmodel.media.MusicVideoViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MusicVideosScreen(navController: NavHostController, viewModel: MusicVideoViewModel = hiltViewModel()) {
    val featured by viewModel.featuredVideos.collectAsState()
    val trending by viewModel.trendingVideos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Music Videos") }) },
        contentPadding = PaddingValues(16.dp)
    ) { padding ->
        if (isLoading) {
            CenteredCircularProgressIndicator(modifier = Modifier.fillMaxSize())
        } else {
            Column(modifier = Modifier.fillMaxSize().padding(padding)) {
                Text("Featured Videos", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(featured) { video ->
                        VideoCard(video)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text("Trending", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(trending) { video ->
                        VideoCard(video)
                    }
                }
            }
        }
    }
}

@Composable
private fun VideoCard(video: MusicVideo) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(request = video.thumbnailUrl ?: ""),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(200.dp).clip(RoundedCornerShape(8.dp))
            )
            Text(text = video.title, style = MaterialTheme.typography.titleMedium)
            Text(text = video.artist, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
            ) {
                Text(text = "${video.durationMs / 60000}:${(video.durationMs % 60000) / 1000}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.PlayCircleOutline, contentDescription = null)
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.FavoriteOutline, contentDescription = null)
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Share, contentDescription = null)
                    }
                }
            }
        }
    }
}
