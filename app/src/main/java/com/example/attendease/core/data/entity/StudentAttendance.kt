package com.example.attendease.core.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// This is your Room entity for the table
@Entity(
    tableName = "student_attendance_table",
    foreignKeys = [
        ForeignKey(
            entity = Attendance::class,
            parentColumns = ["classInfoId", "studentId"],
            childColumns = ["attendanceId_fk"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["attendanceId_fk"])]
)
data class StudentAttendanceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val attendanceId_fk: Long,
    val attendanceCount: Int
)

// This is your data transfer object (DTO) used in your repository
data class StudentAttendance(
    val student: Student,
    val attendanceCount: Int
)