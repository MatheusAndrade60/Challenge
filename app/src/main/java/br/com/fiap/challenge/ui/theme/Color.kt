package br.com.fiap.challenge.ui.theme

import androidx.compose.ui.graphics.Color

sealed class ThemeColors(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val text: Color
)
{
    object Night : ThemeColors(
        background = Color(0xFF000000),  // Preto
        surface = Color(0xFF000000),     // Preto
        primary = Color(0xFF4FB64C),     // Verde
        text = Color(0xFFFFFFFF)         // Branco
    )

    object Day : ThemeColors(
        background = Color(0xFFFFFFFF),  // Branco
        surface = Color(0xFFFFFFFF),     // Branco
        primary = Color(0xFF4FB64C),     // Verde
        text = Color(0xFF000000)         // Preto
    )
}

