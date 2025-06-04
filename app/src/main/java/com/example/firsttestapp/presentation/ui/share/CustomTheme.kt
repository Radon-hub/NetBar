package com.example.firsttestapp.presentation.ui.share

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.firsttestapp.presentation.ui.theme.AppColorScheme
import com.example.firsttestapp.presentation.ui.theme.SpacingScheme
import com.example.firsttestapp.presentation.ui.theme.TypeFaceScheme
import com.example.firsttestapp.presentation.ui.theme.colors
import com.example.firsttestapp.presentation.ui.theme.customTypography
import com.example.firsttestapp.presentation.ui.theme.spacing

object Theme{
    val colors: AppColorScheme
        @Composable
        get() = MaterialTheme.colors

    val typography: TypeFaceScheme
        @Composable
        get() = MaterialTheme.customTypography

    val spacing: SpacingScheme
        @Composable
        get() = MaterialTheme.spacing
}