package com.example.attendease.core.attendies.model.respository

import android.util.Log
import com.example.attendease.core.attendies.model.services.AttendiesService
import com.example.attendease.core.data.dao.AttendanceDao
import com.example.attendease.core.data.dao.ClassInfoDao
import com.example.attendease.core.data.entity.Attendance
import com.example.attendease.core.data.entity.ClassInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.util.Date

class AttendiesRepository(
    private val attendiesService: AttendiesService,
    private val attendanceDao: AttendanceDao,
    private val classInfoDao: ClassInfoDao
) {
    // functions for the 2nd step : selecting concerned session
    fun getAllClassInfo(): Flow<List<ClassInfo>> {
        return classInfoDao.getAllClassInfo()
            .flowOn(Dispatchers.IO)
            .catch { e ->
                Log.e("Repository", "Error fetching class info", e)
                emit(emptyList())
            }
    }

    fun deleteAllClassInfo() {
        return classInfoDao.deleteAllClassInfo()
    }


    fun getClassInfoByDate(date: Date) = classInfoDao.getClassInfoByDate(date)
    fun getClassInfoByGroupOrSection(groupOrSection: String) = classInfoDao.getClassInfoByGroupOrSection(groupOrSection)

    suspend fun setClassInfo(classInfo: ClassInfo) = withContext(Dispatchers.IO) {
        classInfoDao.setClassInfo(classInfo)
    }

    // functions for the 3rd step : marking presence and absence
    fun getAttendanceAll() = attendanceDao.getAttendanceAll()
    fun getAttendanceById(id: Int) = attendanceDao.getAttendanceById(id)
    fun getAttendanceByDate(date: Date) = attendanceDao.getAttendanceByDate(date)
    fun getAttendanceByGroupOrSection(groupOrSection: String) = attendanceDao.getAttendanceByGroupOrSection(groupOrSection)
    fun getAttendanceByDateAndTime(date: Date, timeStart: String, timeEnd: String) = attendanceDao.getAttendanceByDateAndTime(date, timeStart, timeEnd)
    fun getAttendanceByDateAndGroupOrSection(date: Date, groupOrSection: String) = attendanceDao.getAttendanceByDateAndGroupOrSection(groupOrSection, date)
    fun getAttendanceByDateAndTimeAndGroupOrSection(groupOrSection: String, date: Date, timeStart: String, timeEnd: String) = attendanceDao.getAttendanceByDateAndTimeAndGroupOrSection(groupOrSection, date, timeStart, timeEnd)
    // ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    fun getAttendanceWithStudentsAll() = attendanceDao.getAttendanceWithStudentsAll()
    fun getAttendanceWithStudentsById(id: Int) = attendanceDao.getAttendanceWithStudentsById(id)
    fun getAttendanceWithStudentsByDate(date: Date) = attendanceDao.getAttendanceWithStudentsByDate(date)
    fun getAttendanceWithStudentsByDateAndTime(date: Date, timeStart: String, timeEnd: String, groupOrSection: String) = attendanceDao.getAttendanceWithStudentsByDateAndTime(date, timeStart, timeEnd)
    fun getAttendanceWithStudentsByGroupOrSection(groupOrSection: String) = attendanceDao.getAttendanceWithStudentsByGroupOrSection(groupOrSection)
    fun getAttendanceWithStudentsByDateAndGroupOrSection(groupOrSection: String, date: Date) = attendanceDao.getAttendanceWithStudentsByDateAndGroupOrSection(groupOrSection, date)
    fun getAttendanceWithStudentsByDateAndTimeAndGroupOrSection(groupOrSection: String, date: Date, timeStart: String, timeEnd: String) = attendanceDao.getAttendanceWithStudentsByDateAndTimeAndGroupOrSection(groupOrSection, date, timeStart, timeEnd)
    // --------------------------------------------------------------------------------------------------------------------------------
    suspend fun insertAttendance(attendance: Attendance) = attendanceDao.insertAttendance(attendance)
}