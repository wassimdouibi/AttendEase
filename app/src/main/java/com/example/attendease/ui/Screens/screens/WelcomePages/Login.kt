package com.example.attendease.ui.Screens.screens.WelcomePages

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.attendease.R
import com.example.attendease.ui.components.BlueTitleTexte
import com.example.attendease.ui.components.DontHaveAccount
import com.example.attendease.ui.components.GreyTexte
import com.example.attendease.ui.components.MyTextField
import com.example.attendease.ui.components.NormaleGreyTexte
import com.example.attendease.ui.components.passwordTextField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun Login(navController: NavController) {

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
                            .width(290.dp)
                            .height(100.dp)
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
                BlueTitleTexte("Bienvenu")
                Spacer(modifier = Modifier.height(13.dp))
                GreyTexte("Connectez-vous à votre compte AttendEase")
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


                Button(onClick = {

                    if (email.value.isBlank() || password.value.isBlank()) {
                        errorMessage.value = "Tous les champs sont obligatoires"
                        return@Button
                    }

                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(56.dp),

                    contentPadding = PaddingValues(),
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    shape = RoundedCornerShape(100.dp)
                ) {
                    Box (modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(48.dp)
                        .background(
                            color = Color.Black,
                        ),
                        contentAlignment = Alignment.Center)
                    {
                        Text(text = "Connexion")
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.height(15.dp))

            }
        }

    }


