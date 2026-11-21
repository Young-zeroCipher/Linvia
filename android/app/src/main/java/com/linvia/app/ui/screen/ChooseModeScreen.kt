package com.linvia.app.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ChooseModeScreen(navController: NavHostController) {
    var selectedMode by remember { mutableStateOf("dark") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Choose Mode") }) },
        contentPadding = PaddingValues(24.dp)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalArrangement(Arrangement.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Select Your Theme",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ThemeOption(
                    name = "Dark Mode",
                    icon = "🌙",
                    selected = selectedMode == "dark",
                    onClick = { selectedMode = "dark" }
                )
                ThemeOption(
                    name = "Light Mode",
                    icon = "☀️",
                    selected = selectedMode == "light",
                    onClick = { selectedMode = "light" }
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    // Save theme preference
                    navController.navigate("home")
                }
            ) {
                Text("Continue")
            }
        }
    }
}

@Composable
private fun ThemeOption(
    name: String,
    icon: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
            .border(
                if (selected) 2.dp else 1.dp,
                if (selected) MaterialTheme.colorScheme.primary else Color.Unspecified,
                RoundedCornerShape(16.dp)
            )
            .background(
                if (selected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                else MaterialTheme.colorScheme.surface,
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
            .size(150.dp)
            .defaultMinSize(minHeight = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = icon, fontSize = 32.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
            color = if (selected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSurface
        )
    }
}
