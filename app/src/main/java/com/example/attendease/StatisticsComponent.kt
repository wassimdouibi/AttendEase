package com.example.attendease

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



// Theme
@Composable
fun AttendEaseTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        content = content
    )
}

// Enums and Data Classes
enum class FilterType {
    TODAY, WEEKLY, MONTHLY
}

data class GroupPresence(
    val name: String,
    val presencePercentage: Double
)

// All the other supporting composables can be added here
// For brevity, I'll include just a few as examples

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo from drawable
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "AttendEase Logo",
            modifier = Modifier.size(40.dp)
        )

        // Profile Picture from drawable
        Image(
            painter = painterResource(id = R.drawable.a),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun GroupAttendanceChart(
    groupData: List<GroupPresence>,
    selectedFilter: FilterType,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
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
                    style = MaterialTheme.typography.bodySmall
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
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(18.5.dp)
            )
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
            fontFamily = FontFamily.Default,
            fontSize = 14.sp,
            lineHeight = 22.sp,
            color = if (isSelected) Color.White else selectedColor,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun StatisticCards() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Overall Absenteeism Card
        Box(
            modifier = Modifier
                .width(358.dp)
                .height(96.dp)
                .background(
                    color = Color(0xFFE6F1FE),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp)
        ) {
            Text(
                text = "Overall absenteeism rate is 7%",
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                lineHeight = 36.sp,
                color = Color(0xFF171A1F),
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
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = value,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                lineHeight = 30.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.document_normal),
                    contentDescription = "Attendance",
                    tint = Color(0xFF565E6C)
                )
            },
            label = { Text("Attendance") },
            selected = false,
            onClick = { /* TODO: Navigate to Attendance */ },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color(0xFF565E6C),
                selectedIconColor = Color(0xFF006FEE),
                indicatorColor = Color.White
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.chart),
                    contentDescription = "Statistics",
                    tint = Color(0xFF006FEE)
                )
            },
            label = { Text("Statistics") },
            selected = true,
            onClick = { /* Current screen */ },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color(0xFF565E6C),
                selectedIconColor = Color(0xFF006FEE),
                indicatorColor = Color.White
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.info_circle),
                    contentDescription = "Aide & Support",
                    tint = Color(0xFF565E6C)
                )
            },
            label = { Text("Aide & Support") },
            selected = false,
            onClick = { /* TODO: Navigate to Aide & Support */ },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color(0xFF565E6C),
                selectedIconColor = Color(0xFF006FEE),
                indicatorColor = Color.White
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.logout),
                    contentDescription = "Logout",
                    tint = Color(0xFF565E6C)
                )
            },
            label = { Text("Logout") },
            selected = false,
            onClick = { /* TODO: Implement Logout */ },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color(0xFF565E6C),
                selectedIconColor = Color(0xFF006FEE),
                indicatorColor = Color.White
            )
        )
    }
}