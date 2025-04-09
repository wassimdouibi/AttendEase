package com.example.attendease.core.Attendence.model.services


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.attendease.NetworkModule

interface AttendanceService {

    companion object {
        private var attendanceInterface: AttendanceService? = null
        fun getInstance(): AttendanceService {
            if (attendanceInterface == null) {
                attendanceInterface =
                    Retrofit.Builder().baseUrl(NetworkModule.BASE_URL).addConverterFactory(
                        GsonConverterFactory.create()
                    ).build().create(AttendanceService::class.java)
            }
            return attendanceInterface!!
        }
    }
}