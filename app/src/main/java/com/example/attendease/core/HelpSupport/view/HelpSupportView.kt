package com.example.attendease.core.HelpSupport.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.attendease.core.HelpSupport.viewModel.HelpSupportViewModel

@Composable
fun HelpSupportView(
    navController: NavHostController,
    helpSupportViewModel: HelpSupportViewModel
){
    Column {
        Text(
            text = "Help & Support Screen"
        )
    }
}