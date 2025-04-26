package com.example.attendease.core.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class AttendanceWithStudents(
    @Embedded val attendance: Attendance,
    @Relation(
        parentColumn = "attendanceId",
        entityColumn = "attendanceId"
    )
    val studentAttendances: List<StudentAttendance>
)
