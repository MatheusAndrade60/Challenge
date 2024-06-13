package br.com.fiap.challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.challenge.database.repository.UsuarioRepository
import br.com.fiap.challenge.screens.CadastroScreen
import br.com.fiap.challenge.screens.InboxScreen
import br.com.fiap.challenge.screens.LoginScreen
import br.com.fiap.challenge.ui.theme.ChallengeTheme
import br.com.fiap.challenge.screens.WelcomeScreen

class MainActivity : ComponentActivity() {
    private lateinit var usuarioRepository: UsuarioRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicialize o repository antes de configurar o conteúdo
        usuarioRepository = UsuarioRepository(context = this@MainActivity)

        setContent {
            ChallengeTheme {
                // Um container de superfície usando a cor de fundo do tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "welcome") {
                        composable(route = "welcome") {
                            WelcomeScreen(navController)
                        }
                        composable(route = "login") {
                            LoginScreen(navController, usuarioRepository)
                        }
                        composable(route = "cadastro") {
                            CadastroScreen(navController)
                        }
                        composable(route = "inbox") {
                            val sortIconResId = R.drawable.icon
                            InboxScreen(navController, sortIconResId)
                        }
                    }
                }
            }
        }
    }
}
