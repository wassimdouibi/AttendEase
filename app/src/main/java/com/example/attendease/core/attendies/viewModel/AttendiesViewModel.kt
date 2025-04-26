package com.example.attendease.core.attendies.viewModel

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.attendease.core.attendies.model.entity.AttendanceType
import com.example.attendease.core.attendies.model.respository.AttendiesRepository
import com.example.attendease.core.data.AppDataBase
import com.example.attendease.core.data.entity.ClassInfo
import com.example.attendease.core.data.entity.Student
import com.example.attendease.core.data.entity.StudentAttendance
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

    private val _studentsOfAClassWithAttendances = MutableStateFlow<List<StudentAttendance>>(emptyList())
    val studentsOfAClassWithAttendances: StateFlow<List<StudentAttendance>> = _studentsOfAClassWithAttendances.asStateFlow()

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
        classinfoId: Long,
        attendanceType: AttendanceType = AttendanceType.Presence
    ) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null

                val response = attendiesRepository.getStudentsOfAClassWithAttendances(classinfoId, attendanceType)
                _studentsOfAClassWithAttendances.value = response
            } catch (e:Exception) {
                _error.value = e.message ?: "An unknown error occurred in the getStudentsOfAClassWithAttendances function"
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

    fun initializeTestData() {
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
                Student(studentName = "Wassim Douibi", section = "A", groupe = "1"),
                Student(studentName = "Sara Bouzid", section = "A", groupe = "1"),
                Student(studentName = "Nassim Bendib", section = "A", groupe = "1"),
                Student(studentName = "Yasmine Lounis", section = "A", groupe = "1"),
                Student(studentName = "Omar Tighilt", section = "A", groupe = "1"),

                // Group B2
                Student(studentName = "Selim Boualem", section = "B", groupe = "2"),
                Student(studentName = "Meriam Messaoudi", section = "B", groupe = "2"),
                Student(studentName = "Khaled Kacem", section = "B", groupe = "2"),
                Student(studentName = "Farah Ait Ali", section = "B", groupe = "2"),
                Student(studentName = "Imad Berkani", section = "B", groupe = "2"),

                // Group C3
                Student(studentName = "Sofiane Dali", section = "C", groupe = "3"),
                Student(studentName = "Linda Cherif", section = "C", groupe = "3"),
                Student(studentName = "Hichem Bouzid", section = "C", groupe = "3"),
                Student(studentName = "Salima Benzid", section = "C", groupe = "3"),
                Student(studentName = "Mourad Guettache", section = "C", groupe = "3"),

                // Group G2
                Student(studentName = "Amel Boudiaf", section = "G", groupe = "2"),
                Student(studentName = "Karim Hassaine", section = "G", groupe = "2"),
                Student(studentName = "Nour Saadi", section = "G", groupe = "2"),
                Student(studentName = "Walid Mansouri", section = "G", groupe = "2"),
                Student(studentName = "Sabrina Yahiaoui", section = "G", groupe = "2"),

                // Group G1
                Student(studentName = "Rania Belkacem", section = "G", groupe = "1"),
                Student(studentName = "Reda Khodja", section = "G", groupe = "1"),
                Student(studentName = "Sami Chettibi", section = "G", groupe = "1"),
                Student(studentName = "Nesrine Megherbi", section = "G", groupe = "1"),
                Student(studentName = "Yacine Amrani", section = "G", groupe = "1")
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

    class Factory(private val attendiesRepository: AttendiesRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AttendiesViewModel(attendiesRepository) as T
        }
    }
}