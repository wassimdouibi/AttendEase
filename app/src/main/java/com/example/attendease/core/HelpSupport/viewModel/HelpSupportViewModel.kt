package com.example.attendease.core.HelpSupport.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.attendease.core.HelpSupport.model.repository.HelpSupportRepository
import com.example.attendease.core.attendies.model.respository.AttendiesRepository
import com.example.attendease.core.attendies.viewModel.AttendiesViewModel

class HelpSupportViewModel(val helpSupportRepository: HelpSupportRepository) : ViewModel() {


    class Factory(private val helpSupportRepository: HelpSupportRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HelpSupportViewModel(helpSupportRepository) as T
        }
    }
}