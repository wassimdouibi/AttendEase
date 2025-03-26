package com.example.attendease.core.attendies.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme

@Composable
fun SectionHeader(
    title: String,
    subtitle: String? = null,
){
    Column {
        Text(
            text = title,
            style = LocalCustomTypographyScheme.current.heading5,
            color = LocalCustomColorScheme.current.basePrimary
        )
        if (subtitle != null) {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = LocalCustomTypographyScheme.current.p_small,
                color = LocalCustomColorScheme.current.default600
            )
        }
    }

}