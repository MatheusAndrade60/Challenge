package br.com.fiap.challenge.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.challenge.component.Header
import androidx.compose.material3.*
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import br.com.fiap.challenge.ui.theme.ChallengeTheme
import br.com.fiap.challenge.ui.theme.LocalThemeColors


// Exemplo de temas dia/noite
enum class ThemeOption { Day, Night }

@Composable
fun ConfigScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
    val themeColors = LocalThemeColors.current


    var selectedTheme by remember { mutableStateOf(ThemeOption.Day) }
    var primaryColor by remember { mutableStateOf(themeColors.primary) }
    var backgroundColor by remember { mutableStateOf(themeColors.background) }
    var textColor by remember { mutableStateOf(themeColors.text) }
    var selectedFontSize by remember { mutableStateOf(19.sp) }
    var buttonText by remember { mutableStateOf("Salvar Preferências") } // Controle do texto do botão

    LaunchedEffect(Unit) {
        selectedTheme = ThemeOption.valueOf(
            sharedPreferences.getString("theme", ThemeOption.Day.name) ?: ThemeOption.Day.name
        )
        primaryColor = Color(sharedPreferences.getInt("primaryColor", themeColors.primary.toArgb()))
        backgroundColor = Color(sharedPreferences.getInt("backgroundColor", themeColors.background.toArgb()))
        textColor = Color(sharedPreferences.getInt("textColor", themeColors.text.toArgb()))
        selectedFontSize = sharedPreferences.getInt("fontSize", 19).sp
    }
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(3000)
        buttonText = "Salvar Preferências"
    }


    ChallengeTheme(darkTheme = selectedTheme == ThemeOption.Night) {

        fun saveUserPreferences() {
            sharedPreferences.edit().apply {
                putString("theme", selectedTheme.name)
                putInt("primaryColor", primaryColor.toArgb())
                putInt("backgroundColor", backgroundColor.toArgb())
                putInt("textColor", textColor.toArgb())
                putInt("fontSize", selectedFontSize.value.toInt())
                apply() // Salva as mudanças
            }

            // Altera o texto do botão para "Preferências atualizadas!"
            buttonText = "Preferências atualizadas!"

        }

        Column(
            modifier = Modifier
                .background(backgroundColor)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Header(navController = navController)
            Spacer(modifier = Modifier.height(16.dp))

            // Seletor de Tema
            Text(
                text = "Escolha o tema",
                color = textColor,
                fontSize = selectedFontSize
            )
            Row {
                RadioButton(
                    selected = selectedTheme == ThemeOption.Day,
                    onClick = { selectedTheme = ThemeOption.Day }
                )
                Text(
                    text = "Dia",
                    color = textColor,
                    fontSize = selectedFontSize
                )

                Spacer(modifier = Modifier.width(16.dp))
                RadioButton(
                    selected = selectedTheme == ThemeOption.Night,
                    onClick = { selectedTheme = ThemeOption.Night }
                )
                Text(
                    text = "Noite",
                    color = textColor,
                    fontSize = selectedFontSize
                )
            }

            // Seletor de cor primária
            Text(
                text = "Escolha a cor primária",
                color = textColor,
                fontSize = selectedFontSize
            )
            ColorPicker(selectedColor = primaryColor, onColorChange = { primaryColor = it })

            // Seletor de cor de fundo
            Text(
                text = "Escolha a cor de fundo",
                color = textColor,
                fontSize = selectedFontSize
            )
            ColorPicker(selectedColor = backgroundColor, onColorChange = { backgroundColor = it })

            // Seletor de cor do texto
            Text(
                text = "Escolha a cor do texto",
                color = textColor,
                fontSize = selectedFontSize
            )
            ColorPicker(selectedColor = textColor, onColorChange = { textColor = it })

            // Seletor de tamanho da fonte
            Text(
                text = "Escolha o tamanho da fonte",
                color = textColor,
                fontSize = selectedFontSize
            )
            Slider(
                value = selectedFontSize.value,
                onValueChange = { selectedFontSize = it.sp },
                valueRange = 12f..30f,
                colors = SliderDefaults.colors(
                    thumbColor = primaryColor,
                    activeTrackColor = primaryColor
                )
            )
            Text(
                text = "Tamanho da fonte: ${selectedFontSize.value.toInt()}sp",
                color = textColor,
                fontSize = selectedFontSize
            )

            Button(
                onClick = { saveUserPreferences() },
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
            ) {
                Text(buttonText, color = Color.White)  // Exibe o texto do botão
            }
        }
    }
}


@Composable
fun ColorPicker(selectedColor: Color, onColorChange: (Color) -> Unit) {
    // Simulação de seletor de cor (substituir com uma paleta ou seletor real, se necessário)
    Row {
        ColorButton(Color.White, onColorChange)
        Spacer(modifier = Modifier.width(8.dp))
        ColorButton(Color.Gray, onColorChange)
        Spacer(modifier = Modifier.width(8.dp))
        ColorButton(Color.Black, onColorChange)
    }
}

@Composable
fun ColorButton(color: Color, onClick: (Color) -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(color)
            .clickable { onClick(color) }
    )
}

fun saveUserPreferences(
    selectedTheme: ThemeOption,
    primaryColor: Color,
    backgroundColor: Color,
    textColor: Color,
    fontSize: Int
) {
    // Lógica para salvar as preferências (SharedPreferences, banco de dados, etc.)
}


@Preview(showBackground = true)
@Composable
fun PreviewUserPreferencesPage() {
    ConfigScreen(navController = rememberNavController())
}
