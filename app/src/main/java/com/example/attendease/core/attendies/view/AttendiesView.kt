package com.example.attendease.core.attendies.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.attendease.core.attendies.model.entity.AttendanceType
import com.example.attendease.core.attendies.model.entity.ProgressStep
import com.example.attendease.core.attendies.model.entity.Session
import com.example.attendease.core.attendies.model.entity.Student
import com.example.attendease.core.attendies.view.components.*
import com.example.attendease.core.attendies.viewModel.AttendiesViewModel
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*


@Composable
fun AttendiesView(
    navController: NavHostController,
    attendiesViewModel: AttendiesViewModel,
){
    var currentState by remember { mutableStateOf(ProgressStep.Session) }
    var markedByPresence by remember { mutableStateOf(AttendanceType.Presence) }

    val context = LocalContext.current
    var showDatePicker by remember { mutableStateOf(false) }
    var showFilterPopup by remember { mutableStateOf(false) }
    // var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault())
    var selectedDate by remember { mutableStateOf(dateFormat.format(Date())) } // Default to today's date

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
            currentStep = currentState
        )

        if(currentState == ProgressStep.Session) {
            DateSelectorRow(context, selectedDate) { newDate -> selectedDate = newDate }
            SectionHeader(
                title = "Sessions",
                subtitle = "Please select the sessions to proceed with your actions."
            )
            ClassesList(
                listOf(
                    Session(
                        name = "Cours MPSI",
                        salle = "S11+S12",
                        startHour = LocalTime.of(9, 0),   // 09:00 AM
                        endHour = LocalTime.of(10, 30)    // 10:30 AM
                    ),
                    Session(
                        name = "TP BI",
                        salle = "S_Audi",
                        startHour = LocalTime.of(10, 40), // 10:40 AM
                        endHour = LocalTime.of(12, 10)    // 12:10 PM
                    ),
                    Session(
                        name = "Cours AL",
                        salle = "DE-0-A7",
                        startHour = LocalTime.of(12, 45), // 12:45 PM
                        endHour = LocalTime.of(14, 15)    // 02:15 PM
                    ),
                    Session(
                        name = "Cours COFI",
                        salle = "S18+S19",
                        startHour = LocalTime.of(10, 40), // 10:40 AM
                        endHour = LocalTime.of(12, 10)    // 12:10 PM
                    ),
                    Session(
                        name = "Projet PR.IS",
                        salle = "TBD",
                        startHour = LocalTime.of(10, 40), // 10:40 AM
                        endHour = LocalTime.of(12, 10)
                    )
                ),
                onStateChange = { newStep ->
                    currentState = newStep
                }
            )
        } else {
            AttendanceTypes(
                selectedType = AttendanceType.Presence,
                onStateChange = {newValue -> markedByPresence = newValue}
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
                ),
                onStateChange = {newStep -> currentState = newStep}
            )
        }
    }
}