package com.example.attendease.core.statistics.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.sp
import com.example.attendease.R
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme

enum class FilterType {
    TODAY, WEEKLY, MONTHLY
}

data class GroupPresence(
    val name: String,
    val presencePercentage: Double
)

@Composable
fun GroupAttendanceChart(
    groupData: List<GroupPresence>,
    selectedFilter: FilterType,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        groupData.forEach { group ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .width(30.dp)
                        .background(Color(0XFFF3F4F6), shape = RoundedCornerShape(15.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight((group.presencePercentage / 100f).toFloat())
                            .background(Color(0XFF99C7FB), shape = RoundedCornerShape(15.dp))
                            .align(Alignment.BottomCenter)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = group.name,
                    style = LocalCustomTypographyScheme.current.p_tiny
                )
            }
        }
    }
}

@Composable
fun FilterSegmentControl(
    selectedFilter: FilterType,
    onFilterSelected: (FilterType) -> Unit
) {
    Row(
        modifier = Modifier
            .width(358.dp)
            .height(40.dp)
            .background(Color.Transparent)
    ) {
        FilterSegmentButton(
            text = "Today",
            isSelected = selectedFilter == FilterType.TODAY,
            onClick = { onFilterSelected(FilterType.TODAY) },
            selectedColor = Color(0xFF006FEE),
            unselectedColor = Color(0xFF006FEE)
        )
        FilterSegmentButton(
            text = "Weekly",
            isSelected = selectedFilter == FilterType.WEEKLY,
            onClick = { onFilterSelected(FilterType.WEEKLY) },
            selectedColor = Color(0xFF006FEE),
            unselectedColor = Color(0xFF006FEE)
        )
        FilterSegmentButton(
            text = "Monthly",
            isSelected = selectedFilter == FilterType.MONTHLY,
            onClick = { onFilterSelected(FilterType.MONTHLY) },
            selectedColor = Color(0xFF006FEE),
            unselectedColor = Color(0xFF006FEE)
        )
    }
}

@Composable
fun FilterSegmentButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    selectedColor: Color,
    unselectedColor: Color
) {
    Box(
        modifier = Modifier
            .width(if (text == "Weekly") 116.dp else 118.dp)
            .height(36.dp)
            .background(
                color = if (isSelected) selectedColor else Color.Transparent,
                shape = RoundedCornerShape(18.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = LocalCustomTypographyScheme.current.p_large,
            color = if (isSelected) Color.White else selectedColor,
        )
    }
}

@Composable
fun StatisticCards() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Overall Absenteeism Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp)
                .background(
                    color = Color(0xFFE6F1FE),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp)
        ) {
            Text(
                text = "Overall absenteeism rate is 7%",
                style = LocalCustomTypographyScheme.current.heading3,
                color = LocalCustomColorScheme.current.default900,
                modifier = Modifier
                    .width(265.dp)
                    .height(72.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            StatisticCard(
                title = "Most absentee group",
                value = "2CSSIT1",
                backgroundColor = Color(0xFFF31260),
                modifier = Modifier.weight(1f)
            )

            StatisticCard(
                title = "Registered absences",
                value = "50",
                backgroundColor = Color(0xFF006FEE),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun StatisticCard(
    title: String,
    value: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(112.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = LocalCustomTypographyScheme.current.p_large,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = value,
                style = LocalCustomTypographyScheme.current.heading3,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}