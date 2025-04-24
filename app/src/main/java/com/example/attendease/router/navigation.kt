package com.example.attendease.router

import android.content.SharedPreferences
import android.provider.SyncStateContract.Constants
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.attendease.core.HelpSupport.view.HelpSupportView
import com.example.attendease.core.HelpSupport.viewModel.HelpSupportViewModel
import com.example.attendease.core.attendies.view.AttendiesView
import com.example.attendease.core.attendies.viewModel.AttendiesViewModel
import com.example.attendease.core.navigation.view.AttendEaseNavView
import com.example.attendease.core.statistics.view.StatisticsView
import com.example.attendease.core.statistics.viewModel.StatisticsViewModel
import com.example.attendease.onboarding.view.GetStarted
import com.example.attendease.onboarding.view.Login
import com.example.attendease.onboarding.view.LoadingScreen
import com.example.attendease.onboarding.view.OnBoarding

@Composable
fun NavigationHost(
    navController: NavHostController,
    pref: SharedPreferences,

    attendiesViewModel: AttendiesViewModel,
    statisticsViewModel: StatisticsViewModel,
    helpSupportViewModel: HelpSupportViewModel,
){
    NavHost(
        navController = navController,
        startDestination = Router.LoadingScreen.route
    ) {
        composable(Router.GetStartedScreen.route) { // Ensure this matches where it's used
            GetStarted(navController)
        }
        composable(Router.OnBoardingScreen.route) {
            OnBoarding(
                pref = pref,
                navController = navController
            )
        }
        composable(Router.LoginScreen.route) {
            Login(
                pref = pref,
                navController = navController
            )
        }

        composable(Router.LoadingScreen.route) {
            LoadingScreen {
                if(pref.getBoolean("IS_USER_LOGGED_IN", true)){
                    navController.navigate(Router.AttendEaseNavScreen.route)
                } else if(pref.getBoolean("IS_ONBOARDING_DONE", true)) {
                    navController.navigate(Router.LoginScreen.route)
                } else {
                    navController.navigate(Router.GetStartedScreen.route)
                }
            }
        }

        composable(Router.AttendEaseNavScreen.route){
            AttendEaseNavView(
                pref = pref,
                attendiesViewModel = attendiesViewModel,
                statisticsViewModel = statisticsViewModel,
                helpSupportViewModel = helpSupportViewModel
            )
        }

        composable(Router.AttendiesScreen.route) {
            AttendiesView(
                navController = navController,
                attendiesViewModel = attendiesViewModel
            )
        }

        composable(Router.StatisticsScreen.route) {
            StatisticsView(
                navController = navController,
                statisticsViewModel = statisticsViewModel
            )
        }

        composable(Router.HelpSupportScreen.route){
            HelpSupportView(
                navController = navController,
                helpSupportViewModel = helpSupportViewModel
            )
        }
    }
}