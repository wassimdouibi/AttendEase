package com.example.attendease.router

sealed class Router(val route: String) {
    object AttendEaseNavScreen: Router("/nav")
    object AttendiesScreen: Router("/attendies")
    object StatisticsScreen: Router("/statistics")
    object HelpSupportScreen: Router("/helpSupport")
    object AttendanceScreen : Router ("/attendance" )

}