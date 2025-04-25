package com.example.attendease.core.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.attendease.core.attendies.model.entity.AttendanceType
import java.util.Date

@Entity(
    tableName = "attendance_table",
    foreignKeys = [
        ForeignKey(
            entity = ClassInfo::class,
            parentColumns = ["date", "timeStart", "timeEnd", "groupOrSection"],
            childColumns = ["date", "timeStart", "timeEnd", "groupOrSection"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Attendance(
    @PrimaryKey(autoGenerate = true) val attendanceId: Long = 0,
    val date: Date,
    val timeStart: String,
    val timeEnd: String,
    val groupOrSection: String,
    val attendanceType: AttendanceType,
)