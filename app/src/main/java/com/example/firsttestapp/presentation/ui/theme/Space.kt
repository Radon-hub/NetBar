package com.example.firsttestapp.presentation.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Size {
    val x: Dp = 4.dp
    val x2: Dp = 8.dp
    val x3: Dp = 12.dp
    val x4: Dp = 16.dp
    val x5: Dp = 20.dp
    val x6: Dp = 24.dp
    val x7: Dp = 28.dp
    val x8: Dp = 32.dp
    val x9: Dp = 36.dp
    val x10: Dp = 40.dp
}


@Immutable
data class SpacingScheme(
    val extraSmall: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp,
    val cellLarge: Dp
)

fun phoneSpacing(): SpacingScheme =
    SpacingScheme(
        extraSmall = Size.x3,
        small = 14.dp,
        medium = Size.x4,
        large = Size.x5,
        extraLarge = Size.x6,
        cellLarge = Size.x3
    )

val LocalSpacing = compositionLocalOf { phoneSpacing() }

val MaterialTheme.spacing
    @Composable
    @ReadOnlyComposable
    get() =  LocalSpacing.current

