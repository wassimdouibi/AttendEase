package com.example.attendease.core.attendies.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.attendease.core.attendies.model.entity.AttendanceType
import com.example.attendease.core.attendies.model.respository.AttendiesRepository
import com.example.attendease.core.data.entity.Attendance
import com.example.attendease.core.data.entity.ClassInfo
import com.example.attendease.core.data.entity.Student
//import com.example.attendease.core.data.entity.StudentAttendance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class AttendiesViewModel(val attendiesRepository: AttendiesRepository) : ViewModel() {
    private val _classesInfo = MutableStateFlow<List<ClassInfo>>(emptyList())
    val classesInfo: StateFlow<List<ClassInfo>> = _classesInfo.asStateFlow()

    private val _studentsOfAClassWithAttendances = MutableStateFlow<List<Student>>(emptyList())
    val studentsOfAClassWithAttendances: StateFlow<List<Student>> = _studentsOfAClassWithAttendances.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun getAllClassesInfo() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val response = attendiesRepository.getAllClassInfo().first()
                _classesInfo.value = response
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getStudentsOfAClassWithAttendances(
        classInfoId: Long,
        attendanceType: AttendanceType = AttendanceType.Presence
    ) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val response = attendiesRepository.getStudentsOfAClassWithAttendances(classInfoId, attendanceType).first()
                _studentsOfAClassWithAttendances.value = response
                Log.d("Get Students", response.toString())
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteAllClassInfo() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                attendiesRepository.deleteAllClassInfo()
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteAllStudents() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                attendiesRepository.deleteAllStudents()
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun setClassInfo(classInfo: ClassInfo) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                attendiesRepository.setClassInfo(classInfo)
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun initializeTestClassesData() {
        viewModelScope.launch {
            val classes = listOf(
                ClassInfo(
                    title = "Math Class",
                    date = Calendar.getInstance().apply { set(2025, Calendar.APRIL, 25) }.time,
                    timeStart = "08:00",
                    timeEnd = "10:00",
                    groupOrSection = "A1",
                    salle = "Room 101"
                ),
                ClassInfo(
                    title = "Physics Class",
                    date = Calendar.getInstance().apply { set(2025, Calendar.APRIL, 25) }.time,
                    timeStart = "10:30",
                    timeEnd = "12:30",
                    groupOrSection = "B2",
                    salle = "Room 202"
                ),
                ClassInfo(
                    title = "Chemistry Class",
                    date = Calendar.getInstance().apply { set(2025, Calendar.APRIL, 25) }.time,
                    timeStart = "12:30",
                    timeEnd = "15:00",
                    groupOrSection = "C3",
                    salle = "Room 303"
                ),
                ClassInfo(
                    title = "Android Dev",
                    date = Calendar.getInstance().apply { set(2025, Calendar.APRIL, 25) }.time,
                    timeStart = "09:45",
                    timeEnd = "11:00",
                    groupOrSection = "B2",
                    salle = "Room 1"
                ),
                ClassInfo(
                    title = "English Class",
                    date = Calendar.getInstance().apply { set(2025, Calendar.APRIL, 25) }.time,
                    timeStart = "13:00",
                    timeEnd = "14:30",
                    groupOrSection = "G2",
                    salle = "Room 105"
                ),
                ClassInfo(
                    title = "Computer Science",
                    date = Calendar.getInstance().apply { set(2025, Calendar.APRIL, 25) }.time,
                    timeStart = "08:30",
                    timeEnd = "11:30",
                    groupOrSection = "G1",
                    salle = "Lab 404"
                )
            )
            try {
                _isLoading.value = true
                // Use a transaction to insert all classes at once
                classes.forEach { classInfo ->
                    withContext(Dispatchers.IO) {
                        // Use the renamed method from earlier
                        attendiesRepository.setClassInfo(classInfo)
                    }
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun initializeTestDataStudents() {
        viewModelScope.launch {
            val students = listOf(
                // Group A1
                Student(studentName = "Wassim Douibi", section = "A", groupe = "G1"),
                Student(studentName = "Sara Bouzid", section = "A", groupe = "G2"),
                Student(studentName = "Nassim Bendib", section = "A", groupe = "G1"),
                Student(studentName = "Yasmine Lounis", section = "A", groupe = "G1"),
                Student(studentName = "Omar Tighilt", section = "A", groupe = "G2"),

                // Group B2
                Student(studentName = "Selim Boualem", section = "B", groupe = "B2"),
                Student(studentName = "Meriam Messaoudi", section = "B", groupe = "B2"),
                Student(studentName = "Khaled Kacem", section = "B", groupe = "B2"),
                Student(studentName = "Farah Ait Ali", section = "B", groupe = "B2"),
                Student(studentName = "Imad Berkani", section = "B", groupe = "B2"),

                // Group C3
                Student(studentName = "Sofiane Dali", section = "C", groupe = "A1"),
                Student(studentName = "Linda Cherif", section = "C", groupe = "A1"),
                Student(studentName = "Hichem Bouzid", section = "C", groupe = "A1"),
                Student(studentName = "Salima Benzid", section = "C", groupe = "A1"),
                Student(studentName = "Mourad Guettache", section = "C", groupe = "A1"),

                // Group G2
                Student(studentName = "Amel Boudiaf", section = "G", groupe = "G2"),
                Student(studentName = "Karim Hassaine", section = "G", groupe = "G2"),
                Student(studentName = "Nour Saadi", section = "G", groupe = "G2"),
                Student(studentName = "Walid Mansouri", section = "G", groupe = "G2"),
                Student(studentName = "Sabrina Yahiaoui", section = "G", groupe = "G2"),

                // Group G1
                Student(studentName = "Rania Belkacem", section = "G", groupe = "G1"),
                Student(studentName = "Reda Khodja", section = "G", groupe = "G1"),
                Student(studentName = "Sami Chettibi", section = "G", groupe = "G1"),
                Student(studentName = "Nesrine Megherbi", section = "G", groupe = "G1"),
                Student(studentName = "Yacine Amrani", section = "G", groupe = "G1")
            )
            try {
                _isLoading.value = true
                students.forEach { student ->
                    withContext(Dispatchers.IO) {
                        attendiesRepository.setNewStudent(student)
                    }
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun initializeTestAttendancesData() {
        viewModelScope.launch {
            val attendanceData = listOf(
                // Class 1 (Math Class - A1) - Matches original Class 65
                Attendance(classInfoId = 1, studentId = 5, attendanceType = "Absence"),  // Sofiane Dali (265 → 5)
                Attendance(classInfoId = 1, studentId = 6, attendanceType = "Absence"),  // Linda Cherif (266 → 6)
                Attendance(classInfoId = 1, studentId = 7, attendanceType = "Absence"),  // Hichem Bouzid (267 → 7)
                Attendance(classInfoId = 1, studentId = 9, attendanceType = "Absence"),  // Mourad Guettache (269 → 9)

                // Class 2 (Physics Class - B2) - Matches original Class 66
                Attendance(classInfoId = 2, studentId = 10, attendanceType = "Absence"), // Selim Boualem (260 → 10)
                Attendance(classInfoId = 2, studentId = 11, attendanceType = "Absence"), // Meriam Messaoudi (261 → 11)
                Attendance(classInfoId = 2, studentId = 12, attendanceType = "Absence"), // Khaled Kacem (262 → 12)
                Attendance(classInfoId = 2, studentId = 13, attendanceType = "Absence"), // Farah Ait Ali (263 → 13)
                Attendance(classInfoId = 2, studentId = 14, attendanceType = "Absence"), // Imad Berkani (264 → 14)

                // Class 3 (Android Dev - B2) - Matches original Class 68
                Attendance(classInfoId = 3, studentId = 10, attendanceType = "Absence"),
                Attendance(classInfoId = 3, studentId = 11, attendanceType = "Absence"),
                Attendance(classInfoId = 3, studentId = 12, attendanceType = "Absence"),
                Attendance(classInfoId = 3, studentId = 13, attendanceType = "Absence"),
                Attendance(classInfoId = 3, studentId = 14, attendanceType = "Absence"),

                // Class 4 (English Class - G2) - Matches original Class 69
                Attendance(classInfoId = 4, studentId = 2, attendanceType = "Absence"),  // Sara Bouzid (256 → 2)
                Attendance(classInfoId = 4, studentId = 5, attendanceType = "Absence"),  // Omar Tighilt (259 → 5)
                Attendance(classInfoId = 4, studentId = 17, attendanceType = "Absence"), // Nour Saadi (272 → 17)
                Attendance(classInfoId = 4, studentId = 18, attendanceType = "Absence"), // Wal
            )
            try {
                _isLoading.value = true
                _error.value = null

                attendanceData.forEach { attendanceRow ->
                    attendiesRepository.setAttendanceRow(attendanceRow)
                }
            } catch (e: Exception){
                _error.value = e.message ?: "An unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }

    class Factory(private val attendiesRepository: AttendiesRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AttendiesViewModel(attendiesRepository) as T
        }
    }
}