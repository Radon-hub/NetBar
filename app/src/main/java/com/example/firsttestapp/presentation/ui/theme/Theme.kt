package com.example.firsttestapp.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
//    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private fun darkColor(appColor: AppColorScheme) = darkColorScheme(
    primary = appColor.primaryMain,
    secondary = appColor.textPrimary,
    tertiary = appColor.textCancel,
    background = Color.White
)

private fun lightColor(appColor: AppColorScheme) = lightColorScheme(
    primary = appColor.primaryMain,
    secondary = appColor.textPrimary,
    tertiary = appColor.textCancel,
    background = Color.White

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)



@Composable
fun NetBarTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    // condition in different screenSize
    val spacing = phoneSpacing()
    val defaultTypography = defaultTypeFaceScheme()
    val defaultAppColor = defaultAppColor()

    CompositionLocalProvider(
        LocalSpacing provides spacing,
        LocalCustomTypography provides defaultTypography,
        LocalCustomColor provides defaultAppColor
    ) {
        val colorScheme = when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }

            darkTheme -> darkColor(defaultAppColor)
            else -> lightColor(defaultAppColor)
        }


//        Theme(
//            colors = defaultAppColor.copy(
//                primaryMain = colorScheme.primary,
//                primaryDark = colorScheme.secondary,
//                primaryLight = colorScheme.tertiary,
//                primaryContrast = colorScheme.background
//            ),
//            typography = defaultTypography,
//            content = content
//        )

        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography(MaterialTheme.customTypography),
            content = content
        )

    }
}


@Composable
fun FirsttestappTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(MaterialTheme.customTypography),
        content = content
    )
}