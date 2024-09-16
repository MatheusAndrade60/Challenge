import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
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

// Exemplo de temas dia/noite
enum class ThemeOption { Day, Night }

@Composable
fun ConfigScreen(navController: NavController) {
    // Conteúdo da tela de configurações
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Tela de Configurações")

        // Botão de exemplo para navegação
        Button(onClick = { /* Ação ao clicar no botão */ }) {
            Text("Navegar")
        }
    }
}

@Composable
fun UserPreferencesPage(navController: NavController) {
    var selectedTheme by remember { mutableStateOf(ThemeOption.Day) }
    var primaryColor by remember { mutableStateOf(Color(0xFF4FB64C)) }
    var backgroundColor by remember { mutableStateOf(Color.White) }
    var textColor by remember { mutableStateOf(Color.Black) }
    var selectedFontSize by remember { mutableStateOf(16.sp) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Adicionando o Header no topo da página
        Header(navController = navController)

        // Espaçamento após o Header
        Spacer(modifier = Modifier.height(16.dp))

        // Seletor de Tema
        Text("Escolha o tema")
        Row {
            RadioButton(
                selected = selectedTheme == ThemeOption.Day,
                onClick = { selectedTheme = ThemeOption.Day }
            )
            Text("Dia")
            Spacer(modifier = Modifier.width(16.dp))
            RadioButton(
                selected = selectedTheme == ThemeOption.Night,
                onClick = { selectedTheme = ThemeOption.Night }
            )
            Text("Noite")
        }

        // Seletor de cor primária
        Text("Escolha a cor primária")
        ColorPicker(selectedColor = primaryColor, onColorChange = { primaryColor = it })

        // Seletor de cor de fundo
        Text("Escolha a cor de fundo")
        ColorPicker(selectedColor = backgroundColor, onColorChange = { backgroundColor = it })

        // Seletor de cor do texto
        Text("Escolha a cor do texto")
        ColorPicker(selectedColor = textColor, onColorChange = { textColor = it })

        // Seletor de tamanho da fonte
        Text("Escolha o tamanho da fonte")
        Slider(
            value = selectedFontSize.value,
            onValueChange = { selectedFontSize = it.sp },
            valueRange = 12f..30f
        )
        Text("Tamanho da fonte: ${selectedFontSize.value.toInt()}sp")

        // Exemplo de prévia
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Prévia do Texto",
                color = textColor,
                fontSize = selectedFontSize
            )
        }

        // Botão de Salvar Preferências
        Button(onClick = {
            // Salvar as preferências, convertendo `fontSize` para Int
            saveUserPreferences(
                selectedTheme = selectedTheme,
                primaryColor = primaryColor,
                backgroundColor = backgroundColor,
                textColor = textColor,
                fontSize = selectedFontSize.value.toInt() // Converte TextUnit (sp) para Int
            )
        }) {
            Text("Salvar Preferências")
        }
    }
}

@Composable
fun ColorPicker(selectedColor: Color, onColorChange: (Color) -> Unit) {
    // Simulação de seletor de cor (substituir com uma paleta ou seletor real, se necessário)
    Row {
        ColorButton(Color.Red, onColorChange)
        Spacer(modifier = Modifier.width(8.dp))
        ColorButton(Color.Green, onColorChange)
        Spacer(modifier = Modifier.width(8.dp))
        ColorButton(Color.Blue, onColorChange)
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

@Composable
fun PreviewNavController(): NavController {
    return rememberNavController()
}

@Preview(showBackground = true)
@Composable
fun PreviewUserPreferencesPage() {
    UserPreferencesPage(navController = PreviewNavController())
}
