package com.linvia.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SearchScreen(navController: NavHostController) {
    var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { TextField(value = query, onValueChange = { query = it }, singleLine = true, leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }) }
            )
        },
        contentPadding = PaddingValues(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {
            Text("Trending Searches", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(listOf("Billie Eilish", "The Weeknd", "Dua Lipa", "Olivia Rodrigo", "Harry Styles")) { item ->
                    ListItem(
                        text = { Text(item) },
                        onClick = {}
                    )
                }
            }
        }
    }
}
