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
import br.com.fiap.challenge.screens.LoginScreen
import br.com.fiap.challenge.ui.theme.ChallengeTheme
import br.com.fiap.challenge.screens.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val navController = rememberNavController()
//                    NavHost(navController = navController, startDestination = "welcome") {
//                        composable(route = "welcome") {
//                            WelcomeScreen(navController)
//                        }
//                        composable(route = "login") {
//                            LoginScreen(navController)
//                        }
//                    }
                }
            }
        }
    }
}
