package com.example.attendease.core.attendies.model.entity

import java.time.LocalTime

data class Session(
    val name: String,
    val salle: String ,
    val startHour: LocalTime,
    val endHour: LocalTime,
    val type : SessionType = SessionType.Cours
)