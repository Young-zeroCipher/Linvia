package com.linvia.app.ui.screen.media

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.linvia.app.model.media.Audiobook
import com.linvia.app.ui.theme.LinviaGreen
import coil.compose.rememberImagePainter
import com.linvia.app.viewmodel.media.AudiobookViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AudiobooksScreen(navController: NavHostController, viewModel: AudiobookViewModel = hiltViewModel()) {
    val audiobooks by viewModel.featuredAudiobooks.collectAsState()
    val purchased by viewModel.purchasedAudiobooks.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Audiobooks") }) },
        contentPadding = PaddingValues(16.dp)
    ) { padding ->
        if (isLoading) {
            CenteredCircularProgressIndicator(modifier = Modifier.fillMaxSize())
        } else {
            Column(modifier = Modifier.fillMaxSize().padding(padding)) {
                Text("Featured Audiobooks", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(audiobooks) { audiobook ->
                        AudiobookCard(audiobook, onPurchase = {
                            viewModel.purchase(audiobook.id)
                        }, isPurchased = purchased.any { it.id == audiobook.id })
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text("Your Library", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(purchased) { audiobook ->
                        AudiobookCard(audiobook, onPurchase = {}, isPurchased = true)
                    }
                }
            }
        }
    }
}

@Composable
private fun AudiobookCard(
    audiobook: Audiobook,
    onPurchase: () -> Unit,
    isPurchased: Boolean = false
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
                painter = rememberImagePainter(request = audiobook.coverUrl ?: ""),
                contentDescription = null,
                modifier = Modifier.size(64.dp).clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = audiobook.title, style = MaterialTheme.typography.titleMedium)
                Text(text = "by ${audiobook.author}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(text = "Narrated by ${audiobook.narrator ?: "Unknown"}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(text = "${audiobook.chapterCount} chapters", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Spacer(modifier = Modifier.weight(1f))
            if (!isPurchased) {
                Button(
                    onClick = onPurchase,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Buy \$${audiobook.price?.toString() ?: "9.99"}")
                }
            } else {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = LinviaGreen)
                }
            }
        }
    }
}
