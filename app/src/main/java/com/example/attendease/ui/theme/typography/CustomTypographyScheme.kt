package com.example.attendease.ui.theme.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.attendease.ui.theme.interFontFamily

@Immutable
data class CustomTypographyScheme(
    // ------- Heading --------
    val heading1 : TextStyle,
    val heading2 : TextStyle,
    val heading3 : TextStyle,
    val heading4 : TextStyle,
    val heading5 : TextStyle,

    // ------- Paragraph --------
    val p_large : TextStyle,
    val p_largeBold : TextStyle,
    val p_medium : TextStyle,
    val p_mediumBold : TextStyle,
    val p_small : TextStyle,
    val p_smallBold : TextStyle,
    val p_smallSemiBold : TextStyle,
    val p_tiny: TextStyle
)

val defaultCustomTypographyScheme = CustomTypographyScheme(
    heading1 = TextStyle(
        fontFamily = interFontFamily,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold
    ),
    heading2 = TextStyle(
        fontFamily = interFontFamily,
        fontSize = 32.sp,
        fontWeight = FontWeight.Normal
    ),
    heading3 = TextStyle(
        fontFamily = interFontFamily,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    ),
    heading4 = TextStyle(
        fontFamily = interFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),
    heading5 = TextStyle(
        fontFamily = interFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),

    p_large = TextStyle(
        fontFamily = interFontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal
    ),
    p_largeBold = TextStyle(
        fontFamily = interFontFamily,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),
    p_medium = TextStyle(
        fontFamily = interFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    p_mediumBold = TextStyle(
        fontFamily = interFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    ),
    p_small = TextStyle(
        fontFamily = interFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    p_smallBold = TextStyle(
        fontFamily = interFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    ),
    p_smallSemiBold = TextStyle(
        fontFamily = interFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    ),
    p_tiny = TextStyle(
        fontFamily = interFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    )
)