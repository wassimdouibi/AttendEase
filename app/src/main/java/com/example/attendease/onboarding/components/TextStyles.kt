package com.example.attendease.onboarding.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.attendease.ui.theme.LocalCustomColorScheme


@Composable
fun HaveAccount(navController: NavController)
{
    Row (){
        Text(
            text = "Already have an account?",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = Color.Black,
            )
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "Log in",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Normal,
                color = LocalCustomColorScheme.current.primary500,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier
                .clickable {
                    // Navigate to "signIn" screen
                    navController.navigate("Login")
                }
        )
    }
}


@Composable
fun DontHaveAccount(navController: NavController)
{
    Row (){
        Text(
            text = "Vous n'avez pas de compte ?",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = Color.Black,
            )
        )

        Text(
            text = " S'inscrire",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                color = LocalCustomColorScheme.current.primary500,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier
                .clickable {
                    // Navigate to "signUp" screen
                    navController.navigate("")
                }
        )
    }
}


/* Centerd grey text*/
@Composable
fun NormaleGreyTexte (value:String){
    Text(
        text = value,
        modifier = Modifier.fillMaxWidth(), // to  align it to the left
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            color = Color.Gray,
            textAlign = TextAlign.Center,
        )
    )
}

/* not Centerd grey text*/
@Composable
fun GreyTexte (value:String){
    Text(
        text = value,
        modifier = Modifier.fillMaxWidth(), // to  align it to the left
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            color = Color.Gray,
        )
    )
}

@Composable
fun BlackTextCenter (value:String){
    Text(
        text = value,
        modifier = Modifier.fillMaxWidth(), // to  align it to the left
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            color = LocalCustomColorScheme.current.default800,
            textAlign = TextAlign.Center
        )
    )
}

@Composable
fun BlueTitleTexteCenter (value:String){
    Text(
        text = value,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            color = LocalCustomColorScheme.current.primary500
        )
    )
}

@Composable
fun BlueTitleTexte (value:String){
    Text(
        text = value,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            color = LocalCustomColorScheme.current.primary500
        )
    )
}