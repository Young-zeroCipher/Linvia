package com.linvia.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SocialListeningScreen(navController: NavHostController) {
    var roomName by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Social Listening") }) },
        contentPadding = PaddingValues(24.dp)
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Listen Together", style = MaterialTheme.typography.headlineLarge)
            Text(text = "Start a room and invite friends to listen in real-time", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = roomName,
                onValueChange = { roomName = it },
                label = { Text("Room Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(modifier = Modifier.fillMaxWidth(), onClick = {}) {
                Text("Create Room")
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = {}) {
                Text("Join Room by Code")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("Active Rooms", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(listOf("Chill Vibes", "Workout Mix", "90s Throwback")) { room ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        elevation = 2.dp
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.People, contentDescription = null)
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(text = room, style = MaterialTheme.typography.titleMedium)
                                Text(text = "3 listeners • Pop", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Button(onClick = {}) { Text("Join") }
                        }
                    }
                }
            }
        }
    }
}
