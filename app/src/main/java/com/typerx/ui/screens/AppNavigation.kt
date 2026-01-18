package com.typerx.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.fillMaxSize()
    ) {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("typing") {
            TypingScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
        composable("history") {
            HistoryScreen()
        }
        composable("result") { 
            // For now, we'll pass null - in a real app, this would receive the session data
            ResultScreen(session = null)
        }
    }
}