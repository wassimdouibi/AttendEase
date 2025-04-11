package com.example.attendease.core.statistics.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.attendease.core.attendies.model.respository.AttendiesRepository
import com.example.attendease.core.attendies.viewModel.AttendiesViewModel
import com.example.attendease.core.statistics.model.repository.StatisticsRepository

class StatisticsViewModel(val statisticsRepository: StatisticsRepository) : ViewModel() {


    class Factory(private val statisticsRepository: StatisticsRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StatisticsViewModel(statisticsRepository) as T
        }
    }
}