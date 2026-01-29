package fury.typerx

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fury.typerx.ui.screens.HomeScreen
import fury.typerx.ui.screens.TypingScreen

@Composable
fun TyperXApp() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onStartSession = {
                    navController.navigate("typing")
                }
            )
        }
        composable("typing") {
            TypingScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
