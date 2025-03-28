package com.example.attendease.core.Attendence.View
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.attendease.core.Attendence.View.Components.ClassesList
import com.example.attendease.core.Attendence.View.Components.DateSelectorRow
import com.example.attendease.core.Attendence.ViewModel.AttendanceViewModel
import com.example.attendease.core.attendies.model.entity.AttendanceType
import com.example.attendease.core.attendies.model.entity.ProgressStep
import com.example.attendease.core.attendies.model.entity.Student
import com.example.attendease.core.attendies.view.components.AttendanceTypes
import com.example.attendease.core.attendies.view.components.AttendiesStudentList
import com.example.attendease.core.attendies.view.components.ProgressBar
import com.example.attendease.core.attendies.view.components.SectionHeader
import com.example.attendease.ui.theme.LocalCustomColorScheme
import com.example.attendease.ui.theme.LocalCustomTypographyScheme
import com.example.attendease.core.Attendence.model.entity.Session
import com.example.attendease.core.attendies.viewModel.AttendiesViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.*

@Composable
fun AttendanceScreen(
    navController: NavHostController,
    attendanceViewModel: AttendanceViewModel
) {
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
            currentStep = ProgressStep.Date
        )

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

        )
        )


    }
}








