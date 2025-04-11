package com.example.attendease.ui.theme.colors

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColorScheme(
    // -------------- Primary -------------------
    val primary50: Color,
    val primary100: Color,
    val primary200: Color,
    val primary300: Color,
    val primary400: Color,
    val primary500: Color,
    val primary600: Color,
    val primary700: Color,
    val primary800: Color,
    val primary900: Color,

    // -------------- Reference / Base -------------------
    val baseWhite: Color,
    val baseBlack: Color,

    // -------------- Reference / default -------------------
    val default50: Color,
    val default100: Color,
    val default200: Color,
    val default300: Color,
    val default400: Color,
    val default500: Color,
    val default600: Color,
    val default700: Color,
    val default800: Color,
    val default900: Color,

    // -------------- Utility -------------------
    val basePrimary: Color,
    val baseSecondary: Color,
    val baseWarning: Color,
    val baseSuccess: Color,
    val baseError: Color,
)


val defaultCustomColorScheme = CustomColorScheme(
    primary50 = ReferenceBlue50,
    primary100 = ReferenceBlue100,
    primary200 = ReferenceBlue200,
    primary300 = ReferenceBlue300,
    primary400 = ReferenceBlue400,
    primary500 = ReferenceBlue500,
    primary600 = ReferenceBlue600,
    primary700 = ReferenceBlue700,
    primary800 = ReferenceBlue800,
    primary900 = ReferenceBlue900,

    baseWhite = ReferenceBaseWhite,
    baseBlack = ReferenceBaseBlack,

    default50 = ReferenceDefault50,
    default100 = ReferenceDefault100,
    default200 = ReferenceDefault200,
    default300 = ReferenceDefault300,
    default400 = ReferenceDefault400,
    default500 = ReferenceDefault500,
    default600 = ReferenceDefault600,
    default700 = ReferenceDefault700,
    default800 = ReferenceDefault800,
    default900 = ReferenceDefault900,

    basePrimary = baseBlue,
    baseSecondary = basePurple,
    baseWarning = baseYellow,
    baseError = baseRed,
    baseSuccess = baseGreen
)