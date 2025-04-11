package com.example.attendease.core.statistics.view

import com.example.attendease.core.statistics.view.components.GroupPresence
import com.example.attendease.core.statistics.view.components.StatisticCards
import com.example.attendease.core.statistics.view.components.GroupAttendanceChart
import com.example.attendease.core.statistics.view.components.FilterSegmentControl
import com.example.attendease.core.statistics.view.components.FilterType



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.attendease.core.HelpSupport.viewModel.HelpSupportViewModel
import com.example.attendease.core.statistics.viewModel.StatisticsViewModel
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme


@Composable
fun StatisticsView(
    navController: NavHostController,
    statisticsViewModel: StatisticsViewModel
) {
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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {

            Text(
                text = "Statistics",
                textAlign = TextAlign.Start,
                color = LocalCustomColorScheme.current.default900,
                style = LocalCustomTypographyScheme.current.heading1
            )

            Column {

                Text(
                    text = "Average attendance rate per group",
                    style = LocalCustomTypographyScheme.current.p_large,
                    color = LocalCustomColorScheme.current.default600
                )

                Spacer(modifier = Modifier.height(16.dp))

                GroupAttendanceChart(
                    groupData = groupPresenceData,
                    selectedFilter = selectedFilter,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Color.White),

                )

            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
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