package com.linvia.app.ui.screen.social

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.linvia.app.model.Track
import com.linvia.app.model.social.CollaborativePlaylist
import com.linvia.app.ui.theme.LinviaGreen
import coil.compose.rememberImagePainter
import com.linvia.app.viewmodel.social.SocialViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CollaborativePlaylistsScreen(navController: NavHostController, viewModel: SocialViewModel = hiltViewModel()) {
    var playlistName by remember { mutableStateOf("") }
    var playlistDesc by remember { mutableStateOf("") }
    val playlists by viewModel.collaborativePlaylists.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Collaborative Playlists") }) },
        contentPadding = PaddingValues(24.dp)
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "Create Collaborative Playlist", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Invite friends to add songs together", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

            OutlinedTextField(
                value = playlistName,
                onValueChange = { playlistName = it },
                label = { Text("Playlist Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = playlistDesc,
                onValueChange = { playlistDesc = it },
                label = { Text("Description (optional)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.createCollaborativePlaylist(playlistName, playlistDesc) }
            ) {
                Text("Create Playlist")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("Your Collaborative Playlists", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(playlists) { playlist ->
                    CollaborativePlaylistCard(playlist)
                }
            }
        }
    }
}

@Composable
private fun CollaborativePlaylistCard(playlist: CollaborativePlaylist) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(request = playlist.coverUrl ?: ""),
                contentDescription = null,
                modifier = Modifier.size(64.dp).clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = playlist.name, style = MaterialTheme.typography.titleMedium)
                playlist.description?.let {
                    Text(text = it, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Text(text = "${playlist.tracks.size} tracks • ${playlist.memberIds.size} members", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = {}) {
                Icon(Icons.Default.Share, contentDescription = null)
            }
        }
    }
}
