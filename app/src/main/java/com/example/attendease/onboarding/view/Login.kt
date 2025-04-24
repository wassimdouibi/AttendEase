package com.example.attendease.onboarding.view

import android.content.SharedPreferences
import android.provider.SyncStateContract.Constants
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.attendease.R
import com.example.attendease.onboarding.components.BlackButton
import com.example.attendease.onboarding.components.BlueTitleTexte
import com.example.attendease.onboarding.components.GreyTexte
import com.example.attendease.onboarding.components.MyTextField
import com.example.attendease.onboarding.components.passwordTextField
import com.example.attendease.router.Router

@Composable
fun Login(
    pref: SharedPreferences,
    navController: NavController
) {
    var email = remember { mutableStateOf("") }
    var password = remember { mutableStateOf("") }
    var errorMessage = remember { mutableStateOf("") }
    var isLoading = remember { mutableStateOf(false) }
    val composableScope = rememberCoroutineScope()

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()

    ){

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            ){
            Spacer(modifier = Modifier.height(80.dp))
            // Centering the logo image
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "LogoIcon",
                    modifier = Modifier
                        .width(340.dp)
                        .height(100.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))
            BlueTitleTexte("Welcome Back")
            Spacer(modifier = Modifier.height(13.dp))
            GreyTexte("Log in to your AttendEase account")
            if (errorMessage.value.isNotEmpty()) {
                Text(
                    text = errorMessage.value,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            MyTextField("Email", value = email.value, onValueChange = { email.value = it })
            passwordTextField("Password", value = password.value, onValueChange = { password.value = it })
            Spacer(modifier = Modifier.height(4.dp))


            Spacer(modifier = Modifier.height(50.dp))

            BlackButton("Login", navController, Router.AttendEaseNavScreen.route, {pref.edit().putBoolean("IS_USER_LOGGED_IN", true).apply()})

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(15.dp))

        }
    }

}

