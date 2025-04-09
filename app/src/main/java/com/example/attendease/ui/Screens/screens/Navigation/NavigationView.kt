package com.example.attendease.ui.Screens.screens.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.attendease.ui.Screens.screens.WelcomePages.GetStarted
import com.example.attendease.ui.Screens.screens.WelcomePages.Login
import com.example.attendease.ui.Screens.screens.WelcomePages.OnBoarding

@Composable
fun NavigationView() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "GetStarted"  // Ensure route name is consistent
    ) {
        composable("GetStarted") { // Ensure this matches where it's used
            GetStarted(navController)
        }
        composable("Onboarding") {
            OnBoarding(navController)
        }
        composable("Login") {
            Login(navController)
        }
    }
}
