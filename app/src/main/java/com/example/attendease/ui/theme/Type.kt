package com.example.attendease.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.attendease.R


val interFontFamily = FontFamily(
    Font(resId = R.font.inter_black, weight = FontWeight.Black),
    Font(resId = R.font.inter_blackitalic, weight = FontWeight.Black),

    Font(resId = R.font.inter_thin, weight = FontWeight.Black),
    Font(resId = R.font.inter_thinitalic, weight = FontWeight.Black),

    Font(resId = R.font.inter_regular, weight = FontWeight.Normal),

    Font(resId = R.font.inter_medium, weight = FontWeight.Medium),
    Font(resId = R.font.inter_mediumitalic, weight = FontWeight.Black),

    Font(resId = R.font.inter_semibold, weight = FontWeight.SemiBold),
    Font(resId = R.font.inter_semibolditalic, weight = FontWeight.Black),

    Font(resId = R.font.inter_bold, weight = FontWeight.Bold),
    Font(resId = R.font.inter_bolditalic, weight = FontWeight.Black),

    Font(resId = R.font.inter_extrabold, weight = FontWeight.ExtraBold),
    Font(resId = R.font.inter_extrabolditalic, weight = FontWeight.Black),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)