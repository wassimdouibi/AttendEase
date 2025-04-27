package com.example.attendease.core.data.entity
//
//import androidx.room.Entity
//import androidx.room.ForeignKey
//import androidx.room.Index
//
//@Entity(
//    tableName = "attendance_table",
//    primaryKeys = ["classInfoId", "studentId"],
//    foreignKeys = [
//        ForeignKey(
//            entity = ClassInfo::class,
//            parentColumns = ["classInfoId"],
//            childColumns = ["classInfoId"],
//            onDelete = ForeignKey.CASCADE
//        ),
//        ForeignKey(
//            entity = Student::class,
//            parentColumns = ["studentId"],
//            childColumns = ["studentId"],
//            onDelete = ForeignKey.CASCADE
//        )
//    ],
//    indices = [
//        Index(value = ["classInfoId"]),
//        Index(value = ["studentId"])
//    ]
//)
//data class Attendance(
//    val classInfoId: Long,
//    val studentId: Long,
//    val attendanceType: String
//)