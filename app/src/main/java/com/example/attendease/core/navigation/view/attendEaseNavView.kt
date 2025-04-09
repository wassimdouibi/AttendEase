package com.example.attendease.core.navigation.view

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.attendease.core.HelpSupport.view.HelpSupportView
import com.example.attendease.core.HelpSupport.viewModel.HelpSupportViewModel
import com.example.attendease.core.attendies.view.AttendiesView
import com.example.attendease.core.attendies.viewModel.AttendiesViewModel
import com.example.attendease.core.navigation.model.NavItem
import com.example.attendease.core.navigation.view.components.NavItemBox
import com.example.attendease.core.statistics.view.StatisticsView
import com.example.attendease.core.statistics.viewModel.StatisticsViewModel
import com.example.attendease.router.Router
import com.example.attendease.R
import com.example.attendease.core.Attendence.View.AttendanceScreen
import com.example.attendease.core.Attendence.ViewModel.AttendanceViewModel

@Composable
fun AttendEaseNavView(
    pref: SharedPreferences,

    attendanceViewModel: AttendanceViewModel,
    statisticsViewModel: StatisticsViewModel,
    helpSupportViewModel: HelpSupportViewModel
){
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.Transparent,
            ) {
                NavItem.navItems.forEach { item ->
                    NavItemBox(
                        item = item,
                        isSelected = selectedItem == item.id,
                        onClick = {
                            selectedItem = item.id
                            navController.navigate(item.route)
                        },
                    )
                }
            }
        },

        topBar = {
            Image(
                painter = painterResource(R.drawable.logo_nav),
                contentDescription = "logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                alignment = Alignment.CenterStart,
            )
        },

        content = {paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Router.AttendanceScreen.route
                ){
                    composable(route = Router.AttendanceScreen.route){
                        AttendanceScreen(
                            navController = navController,
                            attendanceViewModel = attendanceViewModel
                        )
                    }

                    composable(route = Router.StatisticsScreen.route){
                        StatisticsView(
                            navController = navController,
                            statisticsViewModel = statisticsViewModel,
                        )
                    }

                    composable(route = Router.HelpSupportScreen.route){
                        HelpSupportView(
                            navController = navController,
                            helpSupportViewModel = helpSupportViewModel
                        )
                    }
                }
            }
        }
    )
}