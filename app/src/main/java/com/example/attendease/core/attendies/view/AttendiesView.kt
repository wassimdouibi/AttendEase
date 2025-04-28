package com.example.attendease.core.attendies.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import com.example.attendease.R

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
    var showSuccessDialog by remember { mutableStateOf(false) }

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
//        attendiesViewModel.initializeTestClassesData()
//        attendiesViewModel.initializeTestDataStudents()
//        attendiesViewModel.initializeTestAttendancesData()
//        attendiesViewModel.getStudentsOfAClassWithAttendances(0, AttendanceType.Presence)
        attendiesViewModel.getAllClassesInfo()
    }

    LaunchedEffect(currentState) {
        if (currentState == ProgressStep.Session) {
            attendiesViewModel.getAllClassesInfo()
        }
    }

    LaunchedEffect(markedBy) {
        if (markedBy == AttendanceType.Absence) {
            attendiesViewModel.getStudentsOfAClassWithAttendances(selectedClass?.sessionId ?: 0, markedBy)
        } else {
            attendiesViewModel.getStudentsOfAClassWithAttendances(selectedClass?.sessionId ?: 0, markedBy)
        }
    }

    LaunchedEffect(selectedClass) {
        selectedClass?.let { safeClass ->
            attendiesViewModel.getStudentsOfAClassWithAttendances(safeClass.sessionId, AttendanceType.Absence)
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
                text = stringResource(R.string.attendies_title),
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
                title = stringResource(R.string.attendies_sessions_title),
                subtitle = stringResource(R.string.attendies_sessions_subtitle)
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
                            text = stringResource(R.string.attendies_sessions_none_session),
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
        } else if (currentState == ProgressStep.Attendance) {
            AttendanceTypes(
                selectedType = markedBy,
                onStateChange = {newValue -> markedBy = newValue}
            )
            SectionHeader(
                title = stringResource(R.string.attendies_student_title),
                subtitle = stringResource(R.string.attendies_student_subtitle)
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
                            text = stringResource(R.string.attendies_student_none_student),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                else -> {
                    AttendiesStudentList(
                        studentsListOfClass.map { student ->
                            Student(
                                fullName = student.student.studentName,
                                absenceCount = student.attendanceCount
                            )
                        },
                        onStateChange = { newStep ->
                                currentState = newStep
                                showSuccessDialog = true
                        }
                    )
                }
            }
        } else {
            AlertDialog(
                onDismissRequest = { showSuccessDialog = false },
                title = {
                    Text(
                        text = stringResource(R.string.attendies_popup_success),
                        style = LocalCustomTypographyScheme.current.heading2,
                        color = LocalCustomColorScheme.current.default900
                    )
                },
                text = {
                    Text(
                        text = stringResource(R.string.attendies_popup_message),
                        style = LocalCustomTypographyScheme.current.p_medium,
                        color = LocalCustomColorScheme.current.default700
                    )
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showSuccessDialog = false
                            currentState = ProgressStep.Session
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.attendies_popup_goback),
                            style = LocalCustomTypographyScheme.current.p_mediumSemiBold
                        )
                    }
                },
                containerColor = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp)
            )
        }
    }
}