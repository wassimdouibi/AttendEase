package com.example.attendease.core.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.attendease.core.attendies.model.entity.Student

@Entity(
    tableName = "student_attendance_table",
    primaryKeys = ["attendanceId", "studentName"],
    foreignKeys = [
        ForeignKey(
            entity = Attendance::class,
            parentColumns = ["attendanceId"],
            childColumns = ["attendanceId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class StudentAttendance(
    val attendanceId: Long,
    val studentName: String
)