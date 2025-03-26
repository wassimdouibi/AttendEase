package com.example.attendease.core.HelpSupport.model.services

import com.example.attendease.NetworkModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface HelpSupportService {

    companion object {
        private var helpSupportInterface: HelpSupportService? = null
        fun getInstance(): HelpSupportService {
            if (helpSupportInterface == null) {
                helpSupportInterface =
                    Retrofit.Builder().baseUrl(NetworkModule.BASE_URL).addConverterFactory(
                        GsonConverterFactory.create()
                    ).build().create(HelpSupportService::class.java)
            }
            return helpSupportInterface!!
        }
    }
}