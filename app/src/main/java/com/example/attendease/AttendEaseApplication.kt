package com.example.attendease

import android.app.Application
import android.util.Log
import com.example.attendease.core.HelpSupport.model.repository.HelpSupportRepository
import com.example.attendease.core.HelpSupport.model.services.HelpSupportService
import com.example.attendease.core.attendies.model.respository.AttendiesRepository
import com.example.attendease.core.attendies.model.services.AttendiesService
import com.example.attendease.core.data.AppDataBase
import com.example.attendease.core.data.entity.ClassInfo
import com.example.attendease.core.statistics.model.repository.StatisticsRepository
import com.example.attendease.core.statistics.model.service.StatisticsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar

class AttendEaseApplication: Application() {
    val database: AppDataBase by lazy {
        AppDataBase.buildDatabase(this)!!
    }

    private val attendiesService: AttendiesService by lazy{ AttendiesService.getInstance() }
    val attendiesRepository: AttendiesRepository by lazy { AttendiesRepository(
            attendiesService,
            database.getAttendanceDao(),
            database.getClassInfoDao()
        )
    }

    private val statisticsService: StatisticsService by lazy { StatisticsService.getInstance() }
    val statisticsRepository: StatisticsRepository by lazy { StatisticsRepository(statisticsService) }

    private val helpSupportService: HelpSupportService by lazy { HelpSupportService.getInstance() }
    val helpSupportRepository: HelpSupportRepository by lazy { HelpSupportRepository(helpSupportService) }
}