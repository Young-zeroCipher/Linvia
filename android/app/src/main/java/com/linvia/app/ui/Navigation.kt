package com.linvia.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.linvia.app.ui.screen.HomeScreen
import com.linvia.app.ui.screen.SearchScreen
import com.linvia.app.ui.screen.LibraryScreen
import com.linvia.app.ui.screen.ProfileScreen
import com.linvia.app.ui.screen.NowPlayingScreen
import com.linvia.app.ui.screen.LyricsScreen
import com.linvia.app.ui.screen.SignInScreen
import com.linvia.app.ui.screen.RegisterScreen
import com.linvia.app.ui.screen.ChooseModeScreen
import com.linvia.app.ui.screen.SocialListeningScreen
import com.linvia.app.ui.screen.AIRecommendationsScreen

sealed object Screen {
    const val HOME = "home"
    const val SEARCH = "search"
    const val LIBRARY = "library"
    const val PROFILE = "profile"
    const val NOW_PLAYING = "now_playing"
    const val LYRICS = "lyrics"
    const val SIGN_IN = "sign_in"
    const val REGISTER = "register"
    const val CHOOSE_MODE = "choose_mode"
    const val SOCIAL_LISTENING = "social_listening"
    const val AI_RECOMMENDATIONS = "ai_recommendations"
}

@Composable
fun LinviaNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.CHOOSE_MODE) {
        composable(Screen.CHOOSE_MODE) { ChooseModeScreen(navController) }
        composable(Screen.SIGN_IN) { SignInScreen(navController) }
        composable(Screen.REGISTER) { RegisterScreen(navController) }
        composable(Screen.HOME) { HomeScreen(navController) }
        composable(Screen.SEARCH) { SearchScreen(navController) }
        composable(Screen.LIBRARY) { LibraryScreen(navController) }
        composable(Screen.PROFILE) { ProfileScreen(navController) }
        composable(Screen.NOW_PLAYING) { NowPlayingScreen(navController) }
        composable(Screen.LYRICS) { LyricsScreen(navController) }
        composable(Screen.SOCIAL_LISTENING) { SocialListeningScreen(navController) }
        composable(Screen.AI_RECOMMENDATIONS) { AIRecommendationsScreen(navController) }
    }
}
