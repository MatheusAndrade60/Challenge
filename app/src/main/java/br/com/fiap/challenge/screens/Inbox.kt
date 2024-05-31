package br.com.fiap.challenge.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.challenge.component.Header
import br.com.fiap.challenge.database.repository.getEmailByName
import br.com.fiap.challenge.model.Email

@Composable
fun InboxScreen(navController: NavController){
    var stateEmail by remember {
        mutableStateOf("")
    }
   var listEmailByName by remember {
       mutableStateOf(getEmailByName(stateEmail))
   }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .fillMaxHeight()
    ){
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "Minha Caixa de Entrada",
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Header()

            OutlinedTextField(
                value = stateEmail,
                onValueChange = {
                    stateEmail = it
                    listEmailByName = getEmailByName(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = {
                    Text(text = "Filtre um e-mail")
                },
                trailingIcon = {
                    IconButton(onClick = {})
                    {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "icon-search"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(5.dp))
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight()
                    .background(Color(0xFF535353))
            ) {
                items(listEmailByName) {
                    SchoolCard(email = it)
                }
            }
        }
    }
}
@Composable
fun SchoolCard(email: Email){
    var checked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF2C2C2C))
            .border(
                border = BorderStroke(1.dp, Color.Gray), // Borda para todos os lados
                shape = RectangleShape // Sem borda arredondada
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it },
                colors = CheckboxDefaults.colors(
                    checkmarkColor = Color.White,
                    uncheckedColor = Color.Gray,
                    checkedColor = Color.Gray
                )
            )
            Text(
                text = email.remetente,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = email.titulo,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
//                Column(
//                    modifier = Modifier
//                        .weight(1f)
//                        .fillMaxWidth()
//                        .padding(end = 10.dp),
//                    verticalArrangement = Arrangement.Center
//                ){
//                    Text(
//                        modifier = Modifier
//                            .align(Alignment.CenterHorizontally),
//                        text = "Vagas",
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.Bold,
//                    )
//                    Text(
//                        modifier = Modifier
//                            .align(Alignment.CenterHorizontally),
//                        text = email.quantidadeVagas.toString(),
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Yellow
//                    )
//                }


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun InboxScreenView() {
//    InboxScreen()
//}