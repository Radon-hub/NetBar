package com.example.firsttestapp.presentation.ui.theme

import android.graphics.Color.parseColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

//val Purple80 = Color(0xFFD0BCFF)
//val PurpleGrey80 = Color(0xFFCCC2DC)
//val Pink80 = Color(0xFFEFB8C8)
//
//val Purple40 = Color(0xFF6650a4)
//val PurpleGrey40 = Color(0xFF625b71)
//val Pink40 = Color(0xFF7D5260)

data class AppColorScheme(
    val textPrimary: Color,
    val textSecondary: Color,
    val textWhite: Color,
    val textCancel: Color,

    val primaryMain: Color,

    val backgroundGrayLight: Color,
    val backgroundGrayDark: Color,
    val backgroundWhite: Color,
)

fun defaultAppColor() = AppColorScheme(
    textPrimary = Color(parseColor("#000000")),
    textSecondary = Color(parseColor("#484B52")),
    textWhite = Color(parseColor("#FFFFFF")),
    textCancel = Color(parseColor("#DD0000")),
    primaryMain = Color(parseColor("#FF7A00")),
    backgroundGrayLight = Color(parseColor("#F2F2F2")),
    backgroundGrayDark = Color(parseColor("#DFE1E5")),
    backgroundWhite = Color(parseColor("#FFFFFF"))
)

val LocalCustomColor = compositionLocalOf { defaultAppColor() }

val MaterialTheme.colors
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColor.current


