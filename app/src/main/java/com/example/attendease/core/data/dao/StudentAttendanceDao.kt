//package com.example.attendease.core.data.dao
//
//import androidx.room.Dao
//import androidx.room.Query
//import androidx.room.Transaction
//
//@Dao
//interface StudentAttendanceDao {
//    @Transaction
//    @Query("""
//        SELECT COUNT(*)
//        FROM attendance_table
//        INNER JOIN students_table ON attendance_table.studentId = students_table.studentId
//        WHERE attendance_table.studentId = :studentId
//          AND attendance_table.classInfoId = :classInfoId
//          AND attendanceType = :attendanceType
//    """)
//    fun getNumberOfAttendanceForStudent(
//        studentId: Long,
//        classInfoId: Long,
//        attendanceType: String
//    ): Int
//
//    @Transaction
//    @Query("""
//        SELECT  Count(*) AS numberOfAttendance
//        FROM attendance_table
//        INNER JOIN students_table ON attendance_table.studentId = students_table.studentId
//        WHERE classInfoId = :classInfoId AND attendanceType = :attendanceType
//    """)
//    fun getNumberOfAttendanceForClass(classInfoId: Long, attendanceType: String): Int
//
//    @Transaction
//    @Query("""
//        SELECT Count(*)
//        FROM attendance_table
//        INNER JOIN students_table ON attendance_table.studentId = students_table.studentId
//        WHERE students_table.section = :section
//    """)
//    fun getAttendanceWithDetailsBySection(section: String): Int
//
//    @Transaction
//    @Query("""
//        SELECT Count(*)
//        FROM attendance_table
//        INNER JOIN students_table ON attendance_table.studentId = students_table.studentId
//        WHERE students_table.groupe = :group AND students_table.section = :section
//    """)
//    fun getAttendanceWithDetailsByGroup(
//        group: String,
//        section: String
//    ): Int
//}