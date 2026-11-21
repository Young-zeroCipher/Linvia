package com.linvia.app.ui.screen.media

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.linvia.app.model.media.Podcast
import com.linvia.app.ui.theme.LinviaGreen
import coil.compose.rememberImagePainter
import com.linvia.app.viewmodel.media.PodcastViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PodcastsScreen(navController: NavHostController, viewModel: PodcastViewModel = hiltViewModel()) {
    val podcasts by viewModel.featuredPodcasts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Podcasts") }) },
        contentPadding = PaddingValues(16.dp)
    ) { padding ->
        if (isLoading) {
            CenteredCircularProgressIndicator(modifier = Modifier.fillMaxSize())
        } else {
            Column(modifier = Modifier.fillMaxSize().padding(padding)) {
                Text("Featured Podcasts", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(podcasts) { podcast ->
                        PodcastCard(podcast, onSubscribe = {
                            viewModel.subscribe(podcast.id)
                        })
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text("Your Subscriptions", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(viewModel.subscribedPodcasts.collectAsState().value) { podcast ->
                        PodcastCard(podcast, onSubscribe = {
                            viewModel.unsubscribe(podcast.id)
                        }, isSubscribed = true)
                    }
                }
            }
        }
    }
}

@Composable
private fun PodcastCard(
    podcast: Podcast,
    onSubscribe: () -> Unit,
    isSubscribed: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(request = podcast.coverUrl ?: ""),
                contentDescription = null,
                modifier = Modifier.size(64.dp).clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = podcast.title, style = MaterialTheme.typography.titleMedium)
                Text(text = podcast.publisher, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(text = "${podcast.episodeCount} episodes", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onSubscribe,
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(if (isSubscribed) "Subscribed" else "Subscribe")
            }
        }
    }
}
