package com.linvia.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LyricsScreen(navController: NavHostController) {
    val lyrics = listOf(
        "Sleepin' You're On Your Tippy Toes",
        "Creepin' Around Like No One Knows",
        "Think You're So Criminal",
        "Bruises On Both My Knees For You",
        "Don't Say Thank You Or Please",
        "I Do What I Want When I'm Wanting To",
        "My Soul' So Cynical"
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text("Lyrics") }) },
        contentPadding = PaddingValues(24.dp)
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Bad Guy", style = MaterialTheme.typography.headlineLarge)
            Text(text = "Billie Eilish", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(modifier = Modifier.height(48.dp))

            LazyColumn {
                items(lyrics) { line ->
                    Text(
                        text = line,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth()
                    )
                }
            }
        }
    }
}
