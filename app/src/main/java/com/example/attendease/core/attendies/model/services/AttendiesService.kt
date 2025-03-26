package com.example.attendease.core.attendies.model.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.attendease.NetworkModule
import com.example.attendease.core.HelpSupport.model.services.HelpSupportService

interface AttendiesService {

    companion object {
        private var attendiesInterface: AttendiesService? = null
        fun getInstance(): AttendiesService {
            if (attendiesInterface == null) {
                attendiesInterface =
                    Retrofit.Builder().baseUrl(NetworkModule.BASE_URL).addConverterFactory(
                        GsonConverterFactory.create()
                    ).build().create(AttendiesService::class.java)
            }
            return attendiesInterface!!
        }
    }
}