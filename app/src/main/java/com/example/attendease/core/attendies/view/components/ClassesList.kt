package com.example.attendease.core.attendies.view.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.attendease.core.attendies.model.entity.ProgressStep
import com.example.attendease.core.attendies.model.entity.Session
import com.example.attendease.core.attendies.model.entity.SessionType
import com.example.attendease.core.attendies.model.entity.TimeOfDay
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassesList(
    sessions: List<Session>,
    onStateChange: (ProgressStep) -> Unit,
    onClassSelected: (Session) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var showFilterPopup by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf<SessionType?>(null) }
    var selectedTime by remember { mutableStateOf<TimeOfDay?>(null) }

    // Filter sessions based on search input and selected filters
    val filteredSessions = remember(sessions, searchQuery, selectedType, selectedTime) {
        sessions.filter { session ->
            val matchesSearch = session.name.contains(searchQuery, ignoreCase = true)
            val matchesType = selectedType == null || session.type == selectedType
            val matchesTime = selectedTime?.matches(session.startHour) ?: true

            matchesSearch && matchesType && matchesTime
        }
    }

    Column {
        // Search Bar + Filter Icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text(text = "Search for your session") },
                leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = "Search", tint = LocalCustomColorScheme.current.default900) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = LocalCustomColorScheme.current.default400,
                    unfocusedBorderColor = LocalCustomColorScheme.current.default300
                )
            )

            IconButton(onClick = { showFilterPopup = true }) {
                Icon(Icons.Default.FilterList, contentDescription = "Filter")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredSessions) { session ->
                ClassItem(
                    classItem = session,
                    onStateChange = onStateChange,
                    onClick = { onClassSelected(session) }
                )
            }
        }
        }
    // Show Filter Popup when triggered
    if (showFilterPopup) {
        FilterPopup(
            onDismiss = { showFilterPopup = false },
            selectedType = selectedType,
            onTypeSelected = { selectedType = it },
            selectedTime = selectedTime,
            onTimeSelected = { selectedTime = it }
        )
    }
}
