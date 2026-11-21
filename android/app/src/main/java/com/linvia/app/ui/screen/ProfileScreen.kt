package com.linvia.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Profile") }) },
        contentPadding = PaddingValues(16.dp)
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding), horizontalAlignment = Alignment.CenterHorizontally) {
            CircleImage(
                modifier = Modifier.size(100.dp),
                imageResource = androidx.compose.ui.res.painterResource(id = R.drawable.ic_profile_placeholder)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "LINVIA User", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Free Tier • Member since 2024", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(modifier = Modifier.height(32.dp))

            Button(modifier = Modifier.fillMaxWidth(), onClick = {}) {
                Text("Edit Profile")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Your Library", style = MaterialTheme.typography.titleLarge, modifier = Modifier.align(Alignment.Start))
            Spacer(modifier = Modifier.height(16.dp))
            ListItem(text = { Text("Playlists") }, leadingIcon = { Icon(Icons.Default.LibraryMusic, contentDescription = null) })
            ListItem(text = { Text("Artists") }, leadingIcon = { Icon(Icons.Default.People, contentDescription = null) })
            ListItem(text = { Text("Albums") }, leadingIcon = { Icon(Icons.Default.Album, contentDescription = null) })
        }
    }
}
