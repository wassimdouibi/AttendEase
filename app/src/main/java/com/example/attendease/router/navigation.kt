package com.example.attendease.router

import android.content.SharedPreferences
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
        startDestination = Router.AttendEaseNavScreen.route
    ) {
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