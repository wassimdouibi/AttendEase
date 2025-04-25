package com.example.attendease.core.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "class_info_table",
    primaryKeys = ["date", "timeStart", "timeEnd", "groupOrSection"]
)
data class ClassInfo(
    val title: String,
    val date: Date,
    val timeStart: String,
    val timeEnd: String,
    val groupOrSection: String,
    val salle: String
)
