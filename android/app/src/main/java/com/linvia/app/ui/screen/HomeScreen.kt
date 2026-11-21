package com.linvia.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.linvia.app.model.Track
import com.linvia.app.ui.theme.LinviaGreen
import coil.compose.rememberImagePainter
import com.linvia.app.viewmodel.HomeViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val tracks = listOf(
        Track("1", "Bad Guy", "Billie Eilish", "When We All Fall Asleep", "https://picsum.photos/200", null, 194000, "Pop", 2019),
        Track("2", "Blinding Lights", "The Weeknd", "After Hours", "https://picsum.photos/201", null, 200000, "Pop", 2020),
        Track("3", "Levitating", "Dua Lipa", "Future Nostalgia", "https://picsum.photos/202", null, 203000, "Pop", 2020),
        Track("4", "Stay", "The Kid LAROI, Justin Bieber", "F*ck Love", "https://picsum.photos/203", null, 141000, "Pop", 2021),
        Track("5", "Peaches", "Justin Bieber", "Justice", "https://picsum.photos/204", null, 198000, "Pop", 2021),
        Track("6", "Good 4 U", "Olivia Rodrigo", "SOUR", "https://picsum.photos/205", null, 178000, "Pop", 2021)
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text("LINVIA") }) },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") },
                    selected = true,
                    onClick = {}
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = null) },
                    label = { Text("Search") },
                    selected = false,
                    onClick = { navController.navigate("search") }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                    label = { Text("Library") },
                    selected = false,
                    onClick = { navController.navigate("library") }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = null) },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = { navController.navigate("profile") }
                )
            }
        },
        contentPadding = PaddingValues(16.dp)
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Text("Made for You", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(tracks) { track ->
                    TrackRow(track, onClick = {
                        navController.navigate("now_playing") { 
                            popBackStack("home") 
                            navigate("now_playing?trackId=${track.id}") 
                        }
                    })
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("Quick Access", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth()
            ) {
                item { QuickAccessCard("Podcasts", Icons.Default.Cast, "Listen to your favorite shows") { navController.navigate("podcasts") } }
                item { QuickAccessCard("Audiobooks", Icons.Default.Book, "Premium audiobooks") { navController.navigate("audiobooks") } }
                item { QuickAccessCard("Music Videos", Icons.Default.Videocam, "Watch official videos") { navController.navigate("music_videos") } }
                item { QuickAccessCard("Social Listening", Icons.Default.People, "Listen with friends") { navController.navigate("social_listening") } }
                item { QuickAccessCard("AI Recommendations", Icons.Default.Sparkles, "AI-curated playlists") { navController.navigate("ai_recommendations") } }
                item { QuickAccessCard("Music Recognition", Icons.Default.Mic, "Identify songs instantly") { navController.navigate("music_recognition") } }
                item { QuickAccessCard("Music Charts", Icons.Default.BarChart, "Global & regional") { navController.navigate("music_charts") } }
                item { QuickAccessCard("Concerts", Icons.Default.Event, "Buy tickets & events") { navController.navigate("concerts") } }
            }
        }
    }
}

@Composable
private fun TrackRow(track: Track, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(request = track.albumArtUrl ?: ""),
            contentDescription = null,
            modifier = Modifier.size(56.dp).clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = track.title, style = MaterialTheme.typography.bodyLarge)
            Text(text = track.artist, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(Icons.Default.PlayCircleFilled, contentDescription = "Play", tint = LinviaGreen)
    }
}

@Composable
private fun QuickAccessCard(
    title: String,
    icon: androidx.compose.ui.graphics.ImageVector,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onClick() }
            .padding(8.dp)
            .size(160.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = LinviaGreen,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = androidx.compose.ui.text.TextAlign.Center
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = androidx.compose.ui.text.TextAlign.Center
            )
        }
    }
}
