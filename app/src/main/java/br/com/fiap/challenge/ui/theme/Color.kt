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
        primary = Color.White ,     // Vermelho Locaweb
        text = Color.White         // Branco
    )

    object Day : ThemeColors(
        background = Color.White,  /// Preto ou Branco
        surface = (Color(0xFC243444)),     // Cinza
        primary = Color(0xFFA10328),     // Vermelho Locaweb
        text = Color.White// Preto
    )
}
