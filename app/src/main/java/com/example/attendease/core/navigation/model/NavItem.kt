package com.example.attendease.core.navigation.model

import com.example.attendease.router.Router
import com.example.attendease.R

sealed class NavItem(
    val id: Int,
    val icon: Int,
    val label: String,
    val route: String
){
    object Attendance: NavItem(
        id = 0,
        icon = R.drawable.attendies,
        label = "Attendance",
        route = Router.AttendiesScreen.route
    )

    object Statistics: NavItem(
        id = 1,
        icon = R.drawable.statistics,
        label = "Statistics",
        route = Router.StatisticsScreen.route
    )

    object HelpSupport: NavItem(
        id = 2,
        icon = R.drawable.info_circle,
        label = "Help & Support",
        route = Router.HelpSupportScreen.route
    )

    object Logout: NavItem(
        id = 3,
        icon = R.drawable.logout,
        label = "Logout",
        route = Router.AttendiesScreen.route
    )

    companion object {
        val navItems: List<NavItem> = listOf(
            Attendance, Statistics, HelpSupport, Logout
        )
    }
}