package com.example.firsttestapp.presentation.ui.share

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt

@Composable
fun HorizontalLine(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth()         // Thin vertical line
            .height(1.dp)     // Takes up full height of parent
            .background(Color("#C0C4CC".toColorInt()))
    )
}

@Composable
fun VerticalLine(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .width(1.dp)         // Thin vertical line
            .height(22.dp)     // Takes up full height of parent
            .background(Color("#C0C4CC".toColorInt()))
    )
}

@Composable
fun CircleBox() {
    Box(
        modifier = Modifier
            .padding(end = 15.dp)
            .size(7.dp)
            .background(color = Theme.colors.textPrimary, shape = CircleShape)
    )
}

@Composable
fun SquareBox() {
    Box(
        modifier = Modifier
            .padding(end = 15.dp)
            .size(7.dp)
            .background(color = Theme.colors.textPrimary, shape = RoundedCornerShape(2.dp))
    )
}
