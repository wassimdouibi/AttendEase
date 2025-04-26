package com.example.attendease.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.attendease.core.data.entity.Attendance
import com.example.attendease.core.data.entity.AttendanceWithStudents
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface AttendanceDao {
     @Query("SELECT * FROM attendance_table ORDER BY date, timeStart, timeEnd ASC")
     fun getAttendanceAll(): Flow<List<Attendance>>

     @Query("SELECT * FROM attendance_table WHERE attendanceId = :id")
     fun getAttendanceById(id: Int): Flow<Attendance>

     @Query("SELECT * FROM attendance_table WHERE date = :date ORDER BY timeStart, timeEnd ASC")
     fun getAttendanceByDate(date: Date): Flow<List<Attendance>>

     @Query("SELECT * FROM attendance_table WHERE (date = :date AND timeStart = :timeStart AND timeEnd = :timeEnd) ORDER BY date, timeStart, timeEnd ASC ")
     fun getAttendanceByDateAndTime(date: Date, timeStart: String, timeEnd: String): Flow<List<Attendance>>

     @Query("SELECT * FROM attendance_table WHERE groupOrSection = :groupOrSection ORDER BY date, timeStart, timeEnd ASC")
     fun getAttendanceByGroupOrSection(groupOrSection: String): Flow<List<Attendance>>

     @Query("SELECT * FROM attendance_table WHERE (groupOrSection = :groupOrSection AND date = :date) ORDER BY date, timeStart, timeEnd ASC")
     fun getAttendanceByDateAndGroupOrSection(groupOrSection: String, date: Date): Flow<List<Attendance>>

    @Query("SELECT * FROM attendance_table WHERE (groupOrSection = :groupOrSection AND date = :date AND timeStart = :timeStart AND timeEnd = :timeEnd) ORDER BY date, timeStart, timeEnd ASC")
    fun getAttendanceByDateAndTimeAndGroupOrSection(groupOrSection: String, date: Date, timeStart: String, timeEnd: String): Flow<Attendance>


    @Transaction
    @Query("SELECT * FROM attendance_table ORDER BY date, timeStart, timeEnd ASC")
    fun getAttendanceWithStudentsAll(): Flow<List<AttendanceWithStudents>>

    @Transaction
    @Query("SELECT * FROM attendance_table WHERE attendanceId = :id")
    fun getAttendanceWithStudentsById(id: Int): Flow<AttendanceWithStudents>

    @Transaction
    @Query("SELECT * FROM attendance_table WHERE date = :date ORDER BY timeStart, timeEnd ASC")
    fun getAttendanceWithStudentsByDate(date: Date): Flow<List<AttendanceWithStudents>>

    @Transaction
    @Query("SELECT * FROM attendance_table WHERE (date = :date AND timeStart = :timeStart AND timeEnd = :timeEnd) ORDER BY date, timeStart, timeEnd ASC ")
    fun getAttendanceWithStudentsByDateAndTime(date: Date, timeStart: String, timeEnd: String): Flow<List<AttendanceWithStudents>>

    @Transaction
    @Query("SELECT * FROM attendance_table WHERE groupOrSection = :groupOrSection")
    fun getAttendanceWithStudentsByGroupOrSection(groupOrSection: String): Flow<List<AttendanceWithStudents>>

    @Transaction
    @Query("SELECT * FROM attendance_table WHERE (groupOrSection = :groupOrSection AND date = :date) ORDER BY date, timeStart, timeEnd ASC")
    fun getAttendanceWithStudentsByDateAndGroupOrSection(groupOrSection: String, date: Date): Flow<List<AttendanceWithStudents>>

    @Transaction
    @Query("SELECT * FROM attendance_table WHERE (groupOrSection = :groupOrSection AND date = :date AND timeStart = :timeStart AND timeEnd = :timeEnd) ORDER BY date, timeStart, timeEnd ASC")
    fun getAttendanceWithStudentsByDateAndTimeAndGroupOrSection(groupOrSection: String, date: Date, timeStart: String, timeEnd: String): Flow<AttendanceWithStudents>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAttendance(attendance: Attendance) : Long
}