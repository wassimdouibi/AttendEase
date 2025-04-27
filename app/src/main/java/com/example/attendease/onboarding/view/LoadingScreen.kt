package com.example.attendease.onboarding.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.attendease.R
import com.example.attendease.ui.theme.LocalCustomColorScheme
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(onLoadingComplete: () -> Unit) {
    var progress by remember { mutableStateOf(0f) }
    val statusText = when {
        progress < 0.3f -> stringResource(R.string.loading_screen_msg3)
        progress < 0.7f -> stringResource(R.string.loading_screen_msg4)
        else -> stringResource(R.string.loading_screen_msg5)
    }

    LaunchedEffect(Unit) {
        while (progress < 1f) {
            delay(1000) // Attendre 1 seconde avant d'incrémenter
            progress += 0.3f
        }
        delay(500) // Petite pause avant de naviguer
        onLoadingComplete()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_nav),
            contentDescription = "Loading",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(R.string.loading_screen_msg1),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF007BFF)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.loading_screen_msg2),
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(50.dp))

        AnimatedContent(targetState = statusText) { text ->
            Text(text = text, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(40.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.fillMaxWidth(.8f).height(12.dp),
            color = LocalCustomColorScheme.current.primary500,
            trackColor = LocalCustomColorScheme.current.default200,
        )
    }
}

