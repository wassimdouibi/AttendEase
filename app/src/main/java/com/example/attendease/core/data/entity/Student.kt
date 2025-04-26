package com.example.attendease.core.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students_table")
data class Student(
    @PrimaryKey(autoGenerate = true) val studentId: Long = 0,
    val studentName: String,
    val section: String,
    val groupe: String
)
