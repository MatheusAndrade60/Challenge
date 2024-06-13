package br.com.fiap.challenge.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import br.com.fiap.challenge.component.Header
import br.com.fiap.challenge.database.repository.getEmailByName
import br.com.fiap.challenge.model.Email

@Composable
fun InboxScreen(navController: NavController, sortIconResId: Int) {
    var stateEmail by remember { mutableStateOf("") }
    var listEmailByName by remember { mutableStateOf(getEmailByName(stateEmail)) }
    var selectedEmails by remember { mutableStateOf<LinkedHashSet<Email>>(linkedSetOf()) }

    // Adicione um estado para controlar se a lista está ordenada
    var isSorted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
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
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "icon-search"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(5.dp))

            // Alinhe o botão à esquerda acima da lista
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            ) {
                IconButton(
                    onClick = {
                        isSorted = !isSorted
                        if (isSorted) {
                            listEmailByName = listEmailByName.sortedBy { it.remetente }
                        } else {
                            listEmailByName = getEmailByName(stateEmail)
                        }
                    },
                    modifier = Modifier.align(Alignment.Start)
                ) {
                    Icon(
                        painter = painterResource(id = sortIconResId),
                        contentDescription = if (isSorted) "Desordenar" else "Ordenar"
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight()
                        .background(Color(0xFF535353))
                ) {
                    items(listEmailByName) { email ->
                        var isImportant by remember { mutableStateOf(false) }

                        SchoolCard(
                            email = email,
                            isImportant = isImportant,
                            onStarClicked = { isChecked ->
                                isImportant = isChecked
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SchoolCard(email: Email, isImportant: Boolean, onStarClicked: (Boolean) -> Unit) {
    val backgroundColor = if (isImportant) Color(0xFFF3E035) else Color(0xFF2C2C2C)
    val textColor = if (isImportant) Color.Black else Color.White
    val starColor = if (isImportant) Color.Black else Color.Gray

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .border(
                border = BorderStroke(1.dp, Color.Gray),
                shape = RectangleShape
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconToggleButton(
                checked = isImportant,
                onCheckedChange = { isChecked ->
                    onStarClicked(isChecked)
                }
            ) {
                Icon(
                    imageVector = if (isImportant) Icons.Filled.Star else Icons.Filled.Star,
                    contentDescription = if (isImportant) "Desmarcar estrela" else "Marcar como importante",
                    tint = starColor,
                    modifier = Modifier.size(19.dp)
                )
            }
            Text(
                text = email.remetente,
                fontSize = 19.sp,
                color = textColor
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = email.titulo,
                fontSize = 16.sp,
                color = textColor
            )
        }
    }
}

//@Preview
//@Composable
//private fun InboxScreenView() {
//    InboxScreen()
//}