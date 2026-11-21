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
fun AIRecommendationsScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("AI Recommendations") }) },
        contentPadding = PaddingValues(24.dp)
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.Sparkles, contentDescription = null, tint = LinviaGreen, modifier = Modifier.size(48.dp))
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Discover With LINVIA AI", style = MaterialTheme.typography.headlineLarge)
            Text(text = "Get personalized recommendations based on your mood, listening history, and preferences", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(modifier = Modifier.height(32.dp))

            LazyColumn {
                items(listOf(
                    "Focus & Productivity" to "Lo-fi beats for deep work",
                    "Workout Energy" to "High-tempo tracks for training",
                    "Chill Evening" to "Relaxing melodies for unwinding",
                    "Party Starter" to "Upbeat hits for celebrations",
                    "Sleep Aid" to "Calming sounds for better sleep",
                    "Discovery Weekly" to "New artists we think you'll love",
                    "Mood Booster" to "Upbeat tracks to lift your spirits",
                    "Throwback Thursday" to "Nostalgic hits from the past"
                )) { (title, subtitle) ->
                    RecommendationCard(title, subtitle)
                }
            }
        }
    }
}

@Composable
private fun RecommendationCard(title: String, subtitle: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = title, style = MaterialTheme.typography.titleMedium)
                Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
        }
    }
}
