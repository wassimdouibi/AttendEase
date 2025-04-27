package com.example.attendease.core.HelpSupport.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme
import com.example.attendease.R

@Composable
fun GeneralInstructions() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        InstructionItem(stringResource(R.string.general_instructions_do), true)
        InstructionItem(stringResource(R.string.general_instructions_do), true)
        InstructionItem(stringResource(R.string.general_instructions_do), true)
        InstructionItem(stringResource(R.string.general_instructions_dont), false)
        InstructionItem(stringResource(R.string.general_instructions_dont), false)
    }
}

@Composable
fun InstructionItem(text: String, isPositive: Boolean) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (isPositive)
                Icons.Outlined.CheckCircle
            else
                Icons.Outlined.Cancel,
            contentDescription = if (isPositive)
                "Check"
            else
                "Uncheck",
            tint = if (isPositive)
                Color(29, 215, 91, 255)
            else
                LocalCustomColorScheme.current.baseError,
            modifier = Modifier
                .size(24.dp)
        )
        Text(
            text = text,
            style = LocalCustomTypographyScheme.current.p_medium
        )
    }
}