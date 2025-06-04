package com.example.firsttestapp.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.firsttestapp.R

val iranSansFamily = FontFamily(
    Font(R.font.iransansblack, FontWeight.Black),
    Font(R.font.iransansbold, FontWeight.Bold),
    Font(R.font.iransansbold, FontWeight.ExtraBold),
    Font(R.font.iransanslight, FontWeight.Light),
    Font(R.font.iransansmedium, FontWeight.Medium),
    Font(R.font.iransans, FontWeight.Normal),
    Font(R.font.iransans, FontWeight.SemiBold),
    Font(R.font.iransansultralight, FontWeight.Thin),
)

@Immutable
data class TypeFaceScheme(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val title: TextStyle,
    val label: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
)

fun defaultTypeFaceScheme() = TypeFaceScheme(
    h1 = TextStyle(
        fontFamily = iranSansFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    h2 = TextStyle(
        fontFamily = iranSansFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    h3 = TextStyle(
        fontFamily = iranSansFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    title = TextStyle(
        fontFamily = iranSansFamily,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    label = TextStyle(
        fontFamily = iranSansFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    body1 = TextStyle(
        fontFamily = iranSansFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    body2 = TextStyle(
        fontFamily = iranSansFamily,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    )
)



val LocalCustomTypography = compositionLocalOf { defaultTypeFaceScheme() }

val MaterialTheme.customTypography: TypeFaceScheme
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomTypography.current



fun Typography(customTypeFaceScheme: TypeFaceScheme) = Typography(
    bodyLarge = customTypeFaceScheme.body1,
    headlineMedium = customTypeFaceScheme.body2,
    titleSmall = customTypeFaceScheme.h2,
    headlineSmall = customTypeFaceScheme.h3,
    titleMedium = customTypeFaceScheme.h2,
    titleLarge = customTypeFaceScheme.h1,
    labelSmall = customTypeFaceScheme.label,
    labelMedium = customTypeFaceScheme.label,
    labelLarge = customTypeFaceScheme.title,
)


