package com.example.attendease.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.attendease.core.data.entity.Attendance
import com.example.attendease.core.data.entity.AttendanceWithStudentAndClassInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendanceDao {
     @Query("SELECT * FROM attendance_table")
     fun getAttendanceAll(): Flow<List<Attendance>>

     @Query("SELECT * FROM attendance_table WHERE classInfoId = :classInfoId AND studentId = :studentId")
     fun getStudentAttendanceInSpecificClass(classInfoId: Long, studentId: Long): Flow<Attendance>

     @Query("SELECT * FROM attendance_table WHERE  studentId= :studentId")
     fun getStudentAttendance(studentId: Long): Flow<List<Attendance>>

     @Query("SELECT * FROM attendance_table WHERE classInfoId = :classInfoId")
     fun getAttendanceOfSpecificClass(classInfoId: Long): Flow<List<Attendance>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAttendance(attendance: Attendance) : Long

    @Transaction
    @Query("SELECT * FROM attendance_table")
    fun getAllAttendanceWithDetails(): Flow<List<AttendanceWithStudentAndClassInfo>>

    @Transaction
    @Query("""
        SELECT COUNT(*)
        FROM attendance_table
        INNER JOIN students_table ON attendance_table.studentId = students_table.studentId
        WHERE attendance_table.studentId = :studentId
          AND attendance_table.classInfoId = :classInfoId
          AND attendanceType = :attendanceType
    """)
    fun getNumberOfAttendanceForStudent(
        studentId: Long,
        classInfoId: Long,
        attendanceType: String
    ): Int

    @Transaction
    @Query("""
        SELECT  Count(*) AS numberOfAttendance
        FROM attendance_table
        INNER JOIN students_table ON attendance_table.studentId = students_table.studentId
        WHERE classInfoId = :classInfoId AND attendanceType = :attendanceType
    """)
    fun getNumberOfAttendanceForClass(classInfoId: Long, attendanceType: String): Int

    @Transaction
    @Query("""
        SELECT Count(*)
        FROM attendance_table
        INNER JOIN students_table ON attendance_table.studentId = students_table.studentId
        WHERE students_table.section = :section
    """)
    fun getAttendanceWithDetailsBySection(section: String): Int

    @Transaction
    @Query("""
        SELECT Count(*)
        FROM attendance_table
        INNER JOIN students_table ON attendance_table.studentId = students_table.studentId
        WHERE students_table.groupe = :group AND students_table.section = :section
    """)
    fun getAttendanceWithDetailsByGroup(
        group: String,
        section: String
    ): Int
}