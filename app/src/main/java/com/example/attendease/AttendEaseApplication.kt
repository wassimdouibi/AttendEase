package com.example.attendease
import android.app.Application
import com.example.attendease.core.Attendence.model.repository.AttendanceRepository
import com.example.attendease.core.Attendence.model.services.AttendanceService
import com.example.attendease.core.HelpSupport.model.repository.HelpSupportRepository
import com.example.attendease.core.HelpSupport.model.services.HelpSupportService
import com.example.attendease.core.attendies.model.respository.AttendiesRepository
import com.example.attendease.core.attendies.model.services.AttendiesService
import com.example.attendease.core.statistics.model.repository.StatisticsRepository
import com.example.attendease.core.statistics.model.service.StatisticsService

class AttendEaseApplication: Application() {
    private val attendiesService: AttendiesService by lazy{ AttendiesService.getInstance() }
    val attendiesRepository: AttendiesRepository by lazy { AttendiesRepository(attendiesService) }

    private val statisticsService: StatisticsService by lazy { StatisticsService.getInstance() }
    val statisticsRepository: StatisticsRepository by lazy { StatisticsRepository(statisticsService) }

    private val helpSupportService: HelpSupportService by lazy { HelpSupportService.getInstance() }
    val helpSupportRepository: HelpSupportRepository by lazy { HelpSupportRepository(helpSupportService) }

    private val attendanceService: AttendanceService by lazy{ AttendanceService.getInstance() }
    val attendanceRepository: AttendanceRepository by lazy { AttendanceRepository(attendanceService) }

}