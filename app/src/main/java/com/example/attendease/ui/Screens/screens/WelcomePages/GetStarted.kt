package com.example.attendease.ui.Screens.screens.WelcomePages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.example.attendease.ui.components.BlackButton
import com.example.attendease.ui.components.BlackTextCenter
import com.example.attendease.ui.components.HaveAccount

@Composable
fun GetStarted (navController: NavController,modifier: Modifier = Modifier ) {
    
        var isLoading = remember { mutableStateOf(false) }

        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxSize()
        ){

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
            ){

                Spacer(modifier = Modifier.height(80.dp))
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "LogoIcon",
                    modifier = Modifier
                        .width(290.dp)
                        .height(100.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Image(
                    painter = painterResource(id = R.drawable.man),
                    contentDescription = "manIcon",
                    modifier = Modifier.width(340.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                BlackTextCenter("Là où la présence est simplifiée !")
                Spacer(modifier = Modifier.height(180.dp))
               // Spacer(modifier = Modifier.weight(1f))
                BlackButton("Commencer", navController,"OnBoarding")
                Spacer(modifier = Modifier.height(25.dp))
                HaveAccount(navController)
            }
        }

  
    
    
    
}