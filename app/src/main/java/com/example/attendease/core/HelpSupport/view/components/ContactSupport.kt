package com.example.attendease.core.HelpSupport.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme

@Composable
fun ContactSupport() {
    Column {
        ContactItem(Icons.Outlined.Email, "Email", "support_attendease@esi.dz")
        ContactItem(Icons.Outlined.Phone, "Phone", "+213 540 80 46 94")
        ContactItem(Icons.Outlined.AccessTime, "Office hours", "09:00 - 16:00")
    }
}

@Composable
fun ContactItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = LocalCustomColorScheme.current.primary300,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "$label (",
            color = LocalCustomColorScheme.current.default900,
            style = LocalCustomTypographyScheme.current.p_medium
        )
        Text(
            text = value,
            color = LocalCustomColorScheme.current.primary300,
            style = LocalCustomTypographyScheme.current.p_medium
        )
        Text(
            text = ")",
            color = LocalCustomColorScheme.current.default900,
            style = LocalCustomTypographyScheme.current.p_medium
        )
    }
}