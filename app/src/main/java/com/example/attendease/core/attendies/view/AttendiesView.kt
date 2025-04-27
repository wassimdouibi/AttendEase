package com.example.attendease.core.attendies.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.attendease.core.data.dao.ClassInfoDao
import com.example.attendease.core.data.entity.ClassInfo
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
    // UI States
    val context = LocalContext.current
    var currentState by remember { mutableStateOf(ProgressStep.Session) }
    var markedBy by remember { mutableStateOf(AttendanceType.Presence) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showFilterPopup by remember { mutableStateOf(false) }

    // var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault())
    var selectedDate by remember { mutableStateOf(dateFormat.format(Date())) }

    // Data Flows
    val classesInfo by attendiesViewModel.classesInfo.collectAsState()
    val studentsListOfClass by attendiesViewModel.studentsOfAClassWithAttendances.collectAsState()
    var selectedClass by remember { mutableStateOf<Session?>(null) }


    // State Management
    val isLoading by attendiesViewModel.isLoading.collectAsState()
    val error by attendiesViewModel.error.collectAsState()


    LaunchedEffect(Unit) {
//        attendiesViewModel.deleteAllClassInfo()
//        attendiesViewModel.deleteAllStudents()
//        attendiesViewModel.initializeTestData()
//        attendiesViewModel.initializeTestDataStudents()
        attendiesViewModel.getAllClassesInfo()
    }

    LaunchedEffect(selectedClass) {
        selectedClass?.let { safeClass ->
            attendiesViewModel.getStudentsOfAClassWithAttendances(safeClass.sessionId, AttendanceType.Presence)
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                if (currentState == ProgressStep.Attendance) {
                    currentState = ProgressStep.Session
                }
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to the previous step"
                )
            }

            Text(
                text = "Attendance",
                textAlign = TextAlign.Start,
                color = LocalCustomColorScheme.current.default900,
                style = LocalCustomTypographyScheme.current.heading1
            )
        }

        ProgressBar(
            currentStep = currentState
        )

        if(currentState == ProgressStep.Session) {
            DateSelectorRow(context, selectedDate) { newDate -> selectedDate = newDate }
            SectionHeader(
                title = "Sessions",
                subtitle = "Please select the sessions to proceed with your actions."
            )

            when {
                (isLoading) -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                error != null -> {
                    Text(
                        text = "Error: $error",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                classesInfo.isEmpty() -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No classes available",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                else -> {
                    ClassesList(
                        classesInfo.map { classInfo ->
                            Session(
                                sessionId = classInfo.classInfoId,
                                name = classInfo.title,
                                salle = classInfo.salle,
                                startHour = LocalTime.parse(classInfo.timeStart),
                                endHour = LocalTime.parse(classInfo.timeEnd),
                            )
                        },
                        onStateChange = { newStep ->
                            currentState = newStep
                        },
                        onClassSelected = { newClass ->
                            selectedClass = newClass
                        }
                    )
                }
            }
        } else {
            AttendanceTypes(
                selectedType = markedBy,
                onStateChange = {newValue -> markedBy = newValue}
            )
            SectionHeader(
                title = "Student List",
                subtitle = "Please select the students to proceed with your actions."
            )

            when {
                (isLoading) -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                error != null -> {
                    Text(
                        text = "Error: $error",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                studentsListOfClass.isEmpty() -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "No students available for this class",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                else -> {
                    AttendiesStudentList(
                        studentsListOfClass.map { student ->
                            Student(
                                fullName = student.studentName,
                                absenceCount = 2
                            )
                        },
                        onStateChange = {newStep -> currentState = newStep}
                    )
                }
            }
        }
    }
}


fun parseTimeString(timeString: String): LocalTime {
    return try {
        // Assuming format is "HH:mm"
        val parts = timeString.split(":")
        LocalTime.of(parts[0].toInt(), parts[1].toInt())
    } catch (e: Exception) {
        LocalTime.of(12, 0) // Default fallback time
    }
}