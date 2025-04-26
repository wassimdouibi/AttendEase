package com.example.attendease.core.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "class_info_table",
    indices = [
        Index(
            value = ["date", "timeStart", "timeEnd", "groupOrSection"],
            unique = true
        )
    ]
)
data class ClassInfo(
    @PrimaryKey(autoGenerate = true) val classInfoId: Long = 0,
    val title: String,
    val date: Date,
    val timeStart: String,
    val timeEnd: String,
    val groupOrSection: String,
    val salle: String
)