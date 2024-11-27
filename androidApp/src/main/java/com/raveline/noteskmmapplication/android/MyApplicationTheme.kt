@file:Suppress("IMPLICIT_CAST_TO_ANY")

package com.raveline.noteskmmapplication.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        Colors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5),
            secondaryVariant = Color(0xFF3700B3),
            background = Color(0xFFE0E0E0),
            surface = Color(0xFFE0E0E0),
            error = Color(0xFFCF6679),
            onPrimary = Color(0xFF000000),
            onSecondary = Color(0xFF000000),
            onBackground = Color(0xFF000000),
            onSurface = Color(0xFF000000),
            onError = Color(0xFFFFFFFF),
            isLight = false
        )
    } else {
        Colors(
            primary = Color(0xFF6200EE),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5),
            secondaryVariant = Color(0xFF3700B3),
            background = Color(0xFFE0E0E0),
            surface = Color(0xFFE0E0E0),
            error = Color(0xFFCF6679),
            onPrimary = Color(0xFFFFFFFF),
            onSecondary = Color(0xFF000000),
            onBackground = Color(0xFF000000),
            onSurface = Color(0xFF000000),
            onError = Color(0xFFFFFFFF),
            isLight = true
        )
    }
    val typography = Typography(

    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
