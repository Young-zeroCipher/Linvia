package com.linvia.app.ui.screen.social

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.linvia.app.model.social.Room
import coil.compose.rememberImagePainter
import com.linvia.app.ui.theme.LinviaGreen
import com.linvia.app.viewmodel.social.SocialViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SocialListeningScreen(navController: NavHostController, viewModel: SocialViewModel = hiltViewModel()) {
    var roomName by remember { mutableStateOf("") }
    val rooms by viewModel.activeRooms.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Social Listening") }) },
        contentPadding = PaddingValues(24.dp)
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(24.dp)
        ) {
            Text(text = "Listen Together", style = MaterialTheme.typography.headlineLarge)
            Text(text = "Start a room and invite friends to listen in real-time", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

            OutlinedTextField(
                value = roomName,
                onValueChange = { roomName = it },
                label = { Text("Room Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.createRoom(roomName) }
            ) {
                Text("Create Room")
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {}
            ) {
                Text("Join Room by Code")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("Active Rooms", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CenteredCircularProgressIndicator(modifier = Modifier.fillMaxWidth())
            } else {
                LazyColumn {
                    items(rooms) { room ->
                        RoomCard(room, onJoin = {
                            viewModel.joinRoom(room.inviteCode)
                        })
                    }
                }
            }
        }
    }
}

@Composable
private fun RoomCard(room: Room, onJoin: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = room.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "${room.memberIds.size}/${room.maxMembers} listeners", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = onJoin) {
                Text("Join")
            }
        }
    }
}
