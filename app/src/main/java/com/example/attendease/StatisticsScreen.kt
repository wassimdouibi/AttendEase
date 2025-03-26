package com.example.attendease

import com.example.attendease.StatisticsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen() {
    // State for filter selection
    var selectedFilter by remember { mutableStateOf(FilterType.WEEKLY) }

    // Sample data matching the image
    val groupPresenceData = listOf(
        GroupPresence("1CS1", 70.0),
        GroupPresence("2CSSID", 40.0),
        GroupPresence("1CP7", 60.0),
        GroupPresence("1CS4", 80.0),
        GroupPresence("1CS6", 50.0),
        GroupPresence("2CSSIT1", 30.0),
        GroupPresence("2CP2", 85.0)
    )

    Scaffold(
        containerColor = Color(0xFFFCFDFE),
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFCFDFE))
                .padding(innerPadding)
        ) {
            // Navbar
            TopBar()

            // Statistics Title
            Text(
                text = "Statistics",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineLarge
            )

            // Subtitle
            Text(
                text = "Average attendance rate per group",
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            // Group Attendance Chart
            GroupAttendanceChart(
                groupData = groupPresenceData,
                selectedFilter = selectedFilter,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            // Filter Segment
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    FilterSegmentControl(
                        selectedFilter = selectedFilter,
                        onFilterSelected = { selectedFilter = it }
                    )
                }
            }
            StatisticCards()
        }
    }
}

// Keep all other supporting composables (TopBar, GroupAttendanceChart, FilterSegmentControl,
// StatisticCards, BottomNavigationBar, etc.) in this file as well
// The rest of the code from the original file remains the same...