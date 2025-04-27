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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme
import com.example.attendease.R

@Composable
fun ContactSupport() {
    Column {
        ContactItem(Icons.Outlined.Email, stringResource(R.string.help_support_contact_phone), "support_attendease@esi.dz")
        ContactItem(Icons.Outlined.Phone, stringResource(R.string.help_support_contact_phone), "+213 540 80 46 94")
        ContactItem(Icons.Outlined.AccessTime, stringResource(R.string.help_support_contact_office), "09:00 - 16:00")
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