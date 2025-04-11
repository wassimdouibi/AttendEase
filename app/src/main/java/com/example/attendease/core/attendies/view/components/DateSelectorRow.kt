package com.example.attendease.core.attendies.view.components



import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DateSelectorRow(context: Context, selectedDate: String, onDateChange: (String) -> Unit) {
    val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault())

    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            // Optional: Handle calendar icon click if needed
        }) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.DateRange,
                contentDescription = "Calendar",
                tint = Color.Black // Bold Calendar Icon
            )
        }

        Text(
            text = selectedDate,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
            color = Color.Black
        )

        Text(
            text = "Change",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {
                showDatePicker(context, onDateChange, dateFormat)
            }
        )
    }
}

fun showDatePicker(context: Context, onDateChange: (String) -> Unit, dateFormat: SimpleDateFormat) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
        calendar.set(selectedYear, selectedMonth, selectedDay)
        onDateChange(dateFormat.format(calendar.time)) // Update the selected date
    }, year, month, day).show()
}
