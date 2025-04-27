package com.example.attendease.core.data.entity

data class StudentWithAttendance(
    val student: Student,
    val attendanceCount: Int = 0
)