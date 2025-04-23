package com.example.attendease.router

sealed class Router(val route: String) {
    object AttendEaseNavScreen: Router("/nav")
    object AttendiesScreen: Router("/attendies")
    object StatisticsScreen: Router("/statistics")
    object HelpSupportScreen: Router("/helpSupport")
    object LoadingScreen: Router("/loading")
    object LoginScreen: Router("/login")
    object OnBoardingScreen: Router("/onboarding")
    object GetStartedScreen: Router("/getstarted")
}