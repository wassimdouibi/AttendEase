package com.example.attendease.core.attendies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.attendease.core.attendies.model.respository.AttendiesRepository

class AttendiesViewModel(val attendiesRepository: AttendiesRepository) : ViewModel() {



    class Factory(private val attendiesRepository: AttendiesRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AttendiesViewModel(attendiesRepository) as T
        }
    }
}