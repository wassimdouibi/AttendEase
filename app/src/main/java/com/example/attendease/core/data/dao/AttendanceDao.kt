package com.example.attendease.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.attendease.core.data.entity.Attendance
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendanceDao {
     @Query("SELECT * FROM attendance_table")
     fun getAttendanceAll(): Flow<List<Attendance>>

     @Query("SELECT COUNT(*) FROM attendance_table WHERE classInfoId = :classInfoId AND studentId = :studentId AND attendanceType = :attendanceType")
     fun countStudentAttendanceInSpecificClass(classInfoId: Long, studentId: Long, attendanceType: String): Int

     @Query("SELECT * FROM attendance_table WHERE  studentId= :studentId")
     fun getStudentAttendance(studentId: Long): Flow<List<Attendance>>

    data class AttendanceCount(
        val studentId: Long,
        val count: Int
    )

    @Query("SELECT studentId, COUNT(*) AS count FROM attendance_table WHERE classInfoId = :classInfoId AND attendanceType = :attendanceType GROUP BY studentId")
    fun countAttendanceOfSpecificClass(classInfoId: Long, attendanceType: String): Flow<List<AttendanceCount>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAttendance(attendance: Attendance)

//    @Transaction
//    @Query("SELECT * FROM attendance_table")
//    fun getAllAttendanceWithDetails(): Flow<List<AttendanceWithStudentAndClassInfo>>
}