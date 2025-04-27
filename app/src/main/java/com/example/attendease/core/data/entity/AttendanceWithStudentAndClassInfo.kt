//package com.example.attendease.core.data.entity
//
//import androidx.room.Embedded
//import androidx.room.Relation
//
//data class AttendanceWithStudentAndClassInfo(
//    @Embedded val attendance: Attendance,
//
//    @Relation(
//        parentColumn = "studentId",
//        entityColumn = "studentId"
//    )
//    val student: Student,
//
//    @Relation(
//        parentColumn = "classInfoId",
//        entityColumn = "classInfoId"
//    )
//    val classInfo: ClassInfo
//)
