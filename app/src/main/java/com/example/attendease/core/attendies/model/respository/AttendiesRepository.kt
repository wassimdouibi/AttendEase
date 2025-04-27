package com.example.attendease.core.attendies.model.respository

import android.util.Log
import com.example.attendease.core.attendies.model.entity.AttendanceType
import com.example.attendease.core.attendies.model.services.AttendiesService
import com.example.attendease.core.data.dao.AttendanceDao
import com.example.attendease.core.data.dao.ClassInfoDao
import com.example.attendease.core.data.dao.StudentDao
import com.example.attendease.core.data.entity.Attendance
import com.example.attendease.core.data.entity.ClassInfo
import com.example.attendease.core.data.entity.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext


class AttendiesRepository(
    private val attendiesService: AttendiesService,
    private val attendanceDao: AttendanceDao,
    private val classInfoDao: ClassInfoDao,
    private val studentDao: StudentDao,
//    private val studentAttendanceDao: StudentAttendanceDao
) {
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

    fun deleteAllStudents() {
        return studentDao.deleteAllStudents()
    }

    suspend fun setClassInfo(classInfo: ClassInfo) = withContext(Dispatchers.IO) {
        classInfoDao.setClassInfo(classInfo)
    }

    suspend fun setNewStudent(student: Student) = withContext(Dispatchers.IO) {
        studentDao.insertStudent(student)
    }

    fun getStudentsOfAClassWithAttendances(
        classInfoId: Long,
        attendanceType: AttendanceType
    ): Flow<List<Student>> {
        val studentAttendanceList = mutableListOf<Student>()
        return studentDao.getStudentsByGroup(
            classInfoDao.getClassInfoById(classInfoId).groupOrSection
        ).flowOn(Dispatchers.IO)
        .catch { e ->
            Log.e("Repository", "Error getting student info", e)
            emit(emptyList())
        }
    }

    suspend fun setAttendanceRow(attendanceRow: Attendance) = withContext(Dispatchers.IO) {
        attendanceDao.insertAttendance(attendanceRow)
    }

}
//        val attendances = attendanceDao.getAttendanceOfSpecificClass(classInfoId)
//            .first()

//        attendances.forEach { attendance ->
//            val attendanceCount = studentAttendanceDao.getNumberOfAttendanceForStudent(
//                attendance.studentId,
//                classInfoId,
//                attendanceType.toString()
//            )
//            val studentInfos = studentDao.getStudentById(attendance.studentId)
//
//            studentAttendanceList.add(
//                StudentAttendance(
//                    student = studentInfos,
//                    attendanceCount = attendanceCount
//                )
//            )
//        }