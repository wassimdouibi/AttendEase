package com.example.attendease.core.attendies.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.attendease.core.attendies.viewModel.AttendiesViewModel

@Composable
fun AttendiesView(
    navController: NavHostController,
    attendiesViewModel: AttendiesViewModel
){
    Column {
        Text(
            text = "Attendance",

        )
    }
}