package br.com.fiap.challenge.ui.theme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

sealed class ThemeColors(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val text: Color
) {
    object Night : ThemeColors(
        background = Color.Black,  // Preto
        surface = Color.Black,     // Preto
        primary = Color(0xFF289BC4),     // Verde
        text = Color.White         // Branco
    )

    object Day : ThemeColors(
        background = Color.White,  // Plano de fundo
        surface = Color.White,     // Preenchimentos de fundos
        primary = Color(0xFF289BC4),     // Azul
        text = Color.Black// Preto
    )
}