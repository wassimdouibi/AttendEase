//package com.example.attendease.core.data.dao
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import androidx.room.Transaction
//import com.example.attendease.core.data.entity.Attendance
//import com.example.attendease.core.data.entity.AttendanceWithStudentAndClassInfo
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface AttendanceDao {
//     @Query("SELECT * FROM attendance_table")
//     fun getAttendanceAll(): Flow<List<Attendance>>
//
//     @Query("SELECT * FROM attendance_table WHERE classInfoId = :classInfoId AND studentId = :studentId")
//     fun getStudentAttendanceInSpecificClass(classInfoId: Long, studentId: Long): Flow<Attendance>
//
//     @Query("SELECT * FROM attendance_table WHERE  studentId= :studentId")
//     fun getStudentAttendance(studentId: Long): Flow<List<Attendance>>
//
//     @Query("SELECT * FROM attendance_table WHERE classInfoId = :classInfoId")
//     fun getAttendanceOfSpecificClass(classInfoId: Long): Flow<List<Attendance>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAttendance(attendance: Attendance) : Long
//
//    @Transaction
//    @Query("SELECT * FROM attendance_table")
//    fun getAllAttendanceWithDetails(): Flow<List<AttendanceWithStudentAndClassInfo>>
//}