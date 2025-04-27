package com.example.attendease.core.attendies.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.attendease.core.attendies.model.entity.AttendanceType
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme
import com.example.attendease.R

@Composable
fun AttendanceTypes(
    selectedType: AttendanceType,
    onStateChange: (AttendanceType) -> Unit,
) {
    Column {
        Text(
            text = stringResource(R.string.attendies_types),
            style = LocalCustomTypographyScheme.current.p_largeBold,
            color = LocalCustomColorScheme.current.default900
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AttendanceType.entries.forEachIndexed { _, type ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    RadioButton(
                        selected = selectedType == type,
                        onClick = { onStateChange(type) },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = LocalCustomColorScheme.current.basePrimary,
                            unselectedColor = LocalCustomColorScheme.current.default300,
                            disabledSelectedColor = LocalCustomColorScheme.current.default50
                        )
                    )
                    Text(
                        text = type.name,
                        style = LocalCustomTypographyScheme.current.p_small,
                        color = LocalCustomColorScheme.current.default900
                    )
                }
            }
        }
    }
}