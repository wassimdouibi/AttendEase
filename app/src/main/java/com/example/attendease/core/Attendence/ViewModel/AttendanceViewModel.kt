package com.example.attendease.core.Attendence.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.attendease.core.Attendence.model.repository.AttendanceRepository

class AttendanceViewModel(private val attendanceRepository: AttendanceRepository) : ViewModel() {

    class Factory(private val attendanceRepository: AttendanceRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AttendanceViewModel(attendanceRepository) as T
        }
    }
}
