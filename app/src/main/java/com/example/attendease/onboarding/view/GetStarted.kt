package com.example.attendease.onboarding.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.attendease.R
import com.example.attendease.onboarding.components.BlackButton
import com.example.attendease.onboarding.components.BlackTextCenter
import com.example.attendease.onboarding.components.HaveAccount
import com.example.attendease.router.Router

@Composable
fun GetStarted (navController: NavController,modifier: Modifier = Modifier ) {
    var isLoading = remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "LogoIcon",
            modifier = Modifier.width(340.dp).height(100.dp)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.man),
                contentDescription = "manIcon",
                modifier = Modifier.width(360.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            BlackTextCenter("Where Attendance Made Simple!")
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BlackButton("Get Started", navController, Router.OnBoardingScreen.route)
            Spacer(modifier = Modifier.height(16.dp))
            HaveAccount(navController)
        }
    }
}