package com.example.attendease.core.statistics.model.service

import com.example.attendease.NetworkModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface StatisticsService {

    companion object {
        private var statisticsInterface: StatisticsService? = null
        fun getInstance(): StatisticsService {
            if (statisticsInterface == null) {
                statisticsInterface =
                    Retrofit.Builder().baseUrl(NetworkModule.BASE_URL).addConverterFactory(
                        GsonConverterFactory.create()
                    ).build().create(StatisticsService::class.java)
            }
            return statisticsInterface!!
        }
    }
}