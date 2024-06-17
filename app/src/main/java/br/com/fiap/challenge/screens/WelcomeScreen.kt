package br.com.fiap.challenge.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.challenge.R
import br.com.fiap.challenge.component.Header

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier
            .background(color = Color(0xFFCBD9E6))
            .fillMaxWidth()
            .fillMaxHeight()
        ) {
            //Medoto que ira retornar um layout para o Header
            Header()

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (120).dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF4F6FA)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(modifier = Modifier.padding(
                        vertical = 16.dp,
                        horizontal = 20.dp
                    )
                    ) {
                        Text(
                            text = "Bem-vindo(a) a GoMail",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(15.dp))
                        Button(
                            onClick = {
                                navController.navigate("login")
                            },
                            modifier = Modifier
                                .size(width = 230.dp, height = 40.dp)
                                .align(Alignment.CenterHorizontally),
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFF289BC4))
                        )
                        {
                            Text(
                                text = "INICIAR",
                                fontSize = 15.sp
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(180.dp))

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Banner da Empresa",
                modifier = Modifier.size(320.dp, 190.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop)
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun WelcomeScreenPreview() {
//    WelcomeScreen()
//}