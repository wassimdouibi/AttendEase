package com.example.attendease.core.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.attendease.core.data.entity.StudentAttendance

@Dao
interface StudentAttendanceDao {
    @Query("SELECT * FROM student_attendance_table")
    fun getAllStudentAttendance(): LiveData<List<StudentAttendance>>

    @Query("SELECT * FROM student_attendance_table WHERE attendanceId = :attendanceId")
    fun getAllStudentAttendanceByAttendanceId(attendanceId: String): LiveData<List<StudentAttendance>>

    @Insert
    fun insert(student: StudentAttendance)

    @Insert
    fun insert(students: List<StudentAttendance>)
}