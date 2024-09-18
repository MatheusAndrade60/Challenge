package br.com.fiap.challenge.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

// Definição do CompositionLocal
val LocalThemeColors = staticCompositionLocalOf<ThemeColors> { ThemeColors.Day }

@Composable
fun ChallengeTheme(darkTheme: Boolean = false, content: @Composable () -> Unit) {
    val colors = if (darkTheme) ThemeColors.Night else ThemeColors.Day

    CompositionLocalProvider(LocalThemeColors provides colors) {
        MaterialTheme(
            typography = Typography,
            content = content
        )
    }
}
