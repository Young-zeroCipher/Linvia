package com.linvia.app.ui.screen.discovery

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.linvia.app.model.Track
import coil.compose.rememberImagePainter

@Composable
fun MusicChartsScreen(navController: NavHostController) {
    val charts = listOf(
        ChartItem("Global Top 50", "Worldwide", listOf(
            Track("1", "Bad Guy", "Billie Eilish", "When We All Fall Asleep", "https://picsum.photos/100", null, 194000, "Pop", 2019),
            Track("2", "Blinding Lights", "The Weeknd", "After Hours", "https://picsum.photos/101", null, 200000, "Pop", 2020),
            Track("3", "Levitating", "Dua Lipa", "Future Nostalgia", "https://picsum.photos/102", null, 203000, "Pop", 2020)
        )),
        ChartItem("US Top 50", "United States", listOf(
            Track("1", "As It Was", "Harry Styles", "Harry's House", "https://picsum.photos/103", null, 167000, "Pop", 2022),
            Track("2", "About Damn Time", "Lizzo", "Special", "https://picsum.photos/104", null, 192000, "Pop", 2022),
            Track("3", "First Class", "Jack Harlow", "Come Home the Kids Miss You", "https://picsum.photos/105", null, 178000, "Hip-Hop", 2022)
        )),
        ChartItem("UK Top 50", "United Kingdom", listOf(
            Track("1", "Anti-Hero", "Taylor Swift", "Midnights", "https://picsum.photos/106", null, 206000, "Pop", 2022),
            Track("2", "Unholy", "Sam Smith, Kim Petras", "Unholy", "https://picsum.photos/107", null, 198000, "Pop", 2022),
            Track("3", "Escapism", "Raye, 070 Shake", "My 21st Century Blues", "https://picsum.photos/108", null, 201000, "R&B", 2023)
        ))
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text("Music Charts") }) },
        contentPadding = PaddingValues(16.dp)
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            LazyColumn {
                items(charts) { chart ->
                    ChartSection(chart)
                }
            }
        }
    }
}

data class ChartItem(
    val title: String,
    val region: String,
    val tracks: List<com.linvia.app.model.Track>
)

@Composable
private fun ChartSection(chart: ChartItem) {
    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = chart.title, style = MaterialTheme.typography.titleLarge)
                Text(text = chart.region, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            TextButton(onClick = {}) {
                Text("See All")
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(chart.tracks) { track ->
                ChartTrackCard(track)
            }
        }
    }
}

@Composable
private fun ChartTrackCard(track: com.linvia.app.model.Track) {
    Column(
        modifier = Modifier.width(140.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter(request = track.albumArtUrl ?: ""),
            contentDescription = null,
            modifier = Modifier.size(100.dp).clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = track.title, style = MaterialTheme.typography.bodyLarge, maxLines = 1, overflow = androidx.compose.foundation.layout.TextOverflow.Ellipsis)
        Text(text = track.artist, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1, overflow = androidx.compose.foundation.layout.TextOverflow.Ellipsis)
    }
}
