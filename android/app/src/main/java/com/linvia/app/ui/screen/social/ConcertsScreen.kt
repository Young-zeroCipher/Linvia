package com.linvia.app.ui.screen.social

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.linvia.app.model.social.Concert
import coil.compose.rememberImagePainter
import com.linvia.app.viewmodel.social.SocialViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ConcertsScreen(navController: NavHostController, viewModel: SocialViewModel = hiltViewModel()) {
    val concerts by viewModel.upcomingConcerts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Concerts & Events") }) },
        contentPadding = PaddingValues(16.dp)
    ) { padding ->
        if (isLoading) {
            CenteredCircularProgressIndicator(modifier = Modifier.fillMaxSize())
        } else {
            Column(modifier = Modifier.fillMaxSize().padding(padding)) {
                Text("Upcoming Concerts", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(concerts) { concert ->
                        ConcertCard(concert, onBuyTicket = {
                            viewModel.buyConcertTicket(concert.id)
                        })
                    }
                }
            }
        }
    }
}

@Composable
private fun ConcertCard(concert: Concert, onBuyTicket: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(request = concert.imageUrl ?: ""),
                contentDescription = null,
                modifier = Modifier.size(64.dp).clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = concert.artistName, style = MaterialTheme.typography.titleMedium)
                Text(text = concert.venue, style = MaterialTheme.typography.bodyMedium)
                Text(text = "${concert.city}, ${concert.country}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(text = java.text.SimpleDateFormat("MMM dd, yyyy", java.util.Locale.getDefault()).format(java.util.Date(concert.date)), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                concert.priceRange?.let {
                    Text(text = it, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = onBuyTicket) {
                Text("Buy Ticket")
            }
        }
    }
}
