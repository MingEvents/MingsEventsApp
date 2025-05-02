package com.example.mingseventsapp.menu

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun homeMenu(navController: NavController) {
    HomeMenuContent(
        onNavigateToProfile = { navController.navigate("profile") },
        onNavigateToSettings = { navController.navigate("settings") }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeMenuPreview() {
    HomeMenuContent()
}

@Composable
fun HomeMenuContent(
    onNavigateToProfile: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {}
) {
    Text(text = "Bienvenido al Menú")
}

