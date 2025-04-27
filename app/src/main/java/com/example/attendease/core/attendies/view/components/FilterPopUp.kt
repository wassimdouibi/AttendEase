package com.example.attendease.core.attendies.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.attendease.core.attendies.model.entity.SessionType
import com.example.attendease.core.attendies.model.entity.TimeOfDay
import com.example.attendease.R

@Composable
fun FilterPopup(
    onDismiss: () -> Unit,
    selectedType: SessionType?,
    onTypeSelected: (SessionType?) -> Unit,
    selectedTime: TimeOfDay?,
    onTimeSelected: (TimeOfDay?) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(onClick = onDismiss) { Text(stringResource(R.string.attendies_calendar_applyFilters_cta)) }
        },
        dismissButton = {
            TextButton(onClick = {
                onTypeSelected(null)
                onTimeSelected(null)
                onDismiss()
            }) { Text(stringResource(R.string.attendies_calendar_reset_cta)) }
        },
        title = { Text(stringResource(R.string.attendies_calendar_title_filterSessions)) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                // Session Type Filter
                Text(stringResource(R.string.attendies_calendar_title_sessionType))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    SessionType.entries.forEach { type ->
                        FilterChip(
                            selected = selectedType == type,
                            onClick = { onTypeSelected(if (selectedType == type) null else type) },
                            label = { Text(type.name) }
                        )
                    }
                }

                // Time of Day Filter
                Text(stringResource(R.string.attendies_calendar_title_timeOfDay))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TimeOfDay.entries.forEach { time ->
                        FilterChip(
                            selected = selectedTime == time,
                            onClick = { onTimeSelected(if (selectedTime == time) null else time) },
                            label = { Text(time.name) }
                        )
                    }
                }
            }
        }
    )
}
