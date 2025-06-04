package com.example.firsttestapp.presentation.ui.share

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    end: @Composable (RowScope.() -> Unit)? = null,
    start: @Composable (RowScope.() -> Unit)? = null,
    content: @Composable (BoxScope.() -> Unit)?,
) {

    val typography = Theme.typography
    val color = Theme.colors

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color.backgroundWhite)
            .padding(paddingValues = PaddingValues(vertical = 14.dp, horizontal = 18.dp))
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            content = { start?.invoke(this) }
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(paddingValues = PaddingValues(horizontal = 4.dp)),
            content = {
                CompositionLocalProvider(
                    LocalTextStyle provides typography.h1.copy(color = color.textPrimary)
                ) {
                    content?.invoke(this)
                }
            }
        )
        Row(
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
            content = { end?.invoke(this) }
        )
    }

}
