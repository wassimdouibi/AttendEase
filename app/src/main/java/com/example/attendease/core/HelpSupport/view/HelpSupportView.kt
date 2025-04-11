package com.example.attendease.core.HelpSupport.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.attendease.core.HelpSupport.view.components.ContactSupport
import com.example.attendease.core.HelpSupport.view.components.FAQSection
import com.example.attendease.core.HelpSupport.view.components.GeneralInstructions
import com.example.attendease.core.HelpSupport.viewModel.HelpSupportViewModel
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme

@Composable
fun HelpSupportView(
    navController: NavHostController,
    helpSupportViewModel: HelpSupportViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        // Header
        Text(
            text = "Help & Support",
            textAlign = TextAlign.Start,
            color = LocalCustomColorScheme.current.default900,
            style = LocalCustomTypographyScheme.current.heading1
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // General Instructions
            Text(
                text = "General Instructions",
                style = LocalCustomTypographyScheme.current.p_large,
                color = LocalCustomColorScheme.current.default600
            )

            GeneralInstructions()

            Spacer(modifier = Modifier.height(16.dp))

            // FAQ Section
            Text(
                text = "Frequently Asked Questions",
                style = LocalCustomTypographyScheme.current.p_large,
                color = LocalCustomColorScheme.current.default600
            )
            FAQSection()

            Spacer(modifier = Modifier.height(16.dp))

            // Contact Support
            Text(
                text = "Contact Support",
                style = LocalCustomTypographyScheme.current.p_large,
                color = LocalCustomColorScheme.current.default600
            )
            ContactSupport()
        }
    }
}
