package com.example.attendease.core.Attendence.model.entity

import java.time.LocalTime

enum class TimeOfDay {
    Morning, Evening;

    fun matches(time: LocalTime?): Boolean {
        return when (this) {
            Morning -> time != null && time.hour in 6..11  // 6 AM - 11:59 AM
            Evening -> time != null && time.hour >= 12     // 12 PM onwards
        }
    }
}