package com.example.attendease.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun BlackButton(value: String, navController: NavController, destination: String, setNewValueLocalPreferences: () -> Unit = {}) {
    Button(
        onClick = {
            setNewValueLocalPreferences()
            navController.navigate(destination)
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),

        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Black),
        shape = RoundedCornerShape(100.dp)

    ){
        Box (modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                color = Color.Black,
            ),
            contentAlignment = Alignment.Center)
        {
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}