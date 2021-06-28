package com.gmvalentino.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}

private val DarkColorPalette = darkColors(
    primary = maroon200,
    primaryVariant = maroon700,
    secondary = teal200
)

private val LightColorPalette = lightColors(
    primary = maroon500,
    primaryVariant = maroon700,
    secondary = teal200
)