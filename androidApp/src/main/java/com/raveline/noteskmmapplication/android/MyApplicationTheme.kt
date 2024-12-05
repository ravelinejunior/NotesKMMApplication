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
            primary = Color(0xFF1E88E5), // Darker blue
            primaryVariant = Color(0xFF1565C0), // Even darker blue
            secondary = Color(0xFF26A69A), // Teal
            secondaryVariant = Color(0xFF00796B), // Darker teal
            background = Color(0xFF121212), // Dark gray
            surface = Color(0xFF1D1D1D), // Almost black
            error = Color(0xFFCF6679), // Red (Material Dark Error)
            onPrimary = Color(0xFFFFFFFF), // White for contrast
            onSecondary = Color(0xFFFFFFFF), // White for contrast
            onBackground = Color(0xFFE0E0E0), // Light gray for readability
            onSurface = Color(0xFFE0E0E0), // Light gray
            onError = Color(0xFFFFFFFF), // White
            isLight = false
        )
    } else {
        Colors(
            primary = Color(0xFF3F51B5), // Indigo
            primaryVariant = Color(0xFF303F9F), // Darker indigo
            secondary = Color(0xFFFFC107), // Amber
            secondaryVariant = Color(0xFFFFA000), // Darker amber
            background = Color(0xFFFFFFFF), // White
            surface = Color(0xFFFFFFFF), // White
            error = Color(0xFFD32F2F), // Red (Material Light Error)
            onPrimary = Color(0xFFFFFFFF), // White
            onSecondary = Color(0xFF000000), // Black
            onBackground = Color(0xFF000000), // Black
            onSurface = Color(0xFF000000), // Black
            onError = Color(0xFFFFFFFF), // White
            isLight = true
        )
    }
    val typography = Typography()

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
