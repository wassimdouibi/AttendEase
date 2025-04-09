package com.example.attendease.core.attendies.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.attendease.core.attendies.model.entity.AttendanceType
import com.example.attendease.core.attendies.model.entity.ProgressStep
import com.example.attendease.core.attendies.model.entity.Student
import com.example.attendease.core.attendies.view.components.AttendanceTypes
import com.example.attendease.core.attendies.view.components.AttendiesStudentList
import com.example.attendease.core.attendies.view.components.ProgressBar
import com.example.attendease.core.attendies.view.components.SectionHeader
import com.example.attendease.core.attendies.viewModel.AttendiesViewModel
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme


@Composable
fun AttendiesView(
    navController: NavHostController,
    attendiesViewModel: AttendiesViewModel
){
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Text(
            text = "Attendance",
            textAlign = TextAlign.Start,
            color = LocalCustomColorScheme.current.default900,
            style = LocalCustomTypographyScheme.current.heading1
        )
        ProgressBar(
            currentStep = ProgressStep.Attendance
        )
        AttendanceTypes(
            selectedType = AttendanceType.Presence,
            onClick = {}
        )
        SectionHeader(
            title = "Student List",
            subtitle = "Please select the students to proceed with your actions."
        )
        AttendiesStudentList(
            listOf(
                Student("Wassim Douibi", 0),
                Student("Derbal Rayhane", 4),
                Student("Brahimi Thenina", 3),
                Student("Boumaout Farah", 1),
                Student("Wassim Douibi", 0),
                Student("Derbal Rayhane", 4),
                Student("Brahimi Thenina", 3),
                Student("Boumaout Farah", 1),
                Student("Wassim Douibi", 0),
                Student("Derbal Rayhane", 4),
                Student("Brahimi Thenina", 3),
                Student("Boumaout Farah", 1),
                Student("Wassim Douibi", 0),
                Student("Derbal Rayhane", 4),
                Student("Brahimi Thenina", 3),
                Student("Boumaout Farah", 1),
                Student("Wassim Douibi", 0),
                Student("Derbal Rayhane", 4),
                Student("Brahimi Thenina", 3),
                Student("Boumaout Farah", 1)
            )
        )
    }
}