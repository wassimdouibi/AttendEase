package com.example.attendease.core.attendies.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.attendease.core.attendies.model.entity.ProgressStep
import com.example.attendease.core.attendies.view.components.ProgressBar
import com.example.attendease.core.attendies.viewModel.AttendiesViewModel
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme


@Composable
fun AttendiesView(
    navController: NavHostController,
    attendiesViewModel: AttendiesViewModel
){
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Attendance",
            textAlign = TextAlign.Start,
            color = LocalCustomColorScheme.current.default900,
            style = LocalCustomTypographyScheme.current.heading1
        )
        ProgressBar(ProgressStep.Attendance)
    }
}