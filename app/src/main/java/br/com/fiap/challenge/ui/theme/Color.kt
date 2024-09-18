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
        background = Color(0xFF080C12 ),  // Preto
        surface = Color.Black,     // Preto
        primary = Color(0xFF289BC4),     // Verde
        text = Color.White         // Branco
    )

    object Day : ThemeColors(
        background = Color(0xFF2C343C),  // Plano de fundo
        surface = Color.White,     // Preenchimentos de fundos
        primary = Color(0xFFA10328),     // Vermelho Locaweb
        text = Color.Black// Preto
    )
}
