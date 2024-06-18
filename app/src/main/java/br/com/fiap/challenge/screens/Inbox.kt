package br.com.fiap.challenge.screens

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import br.com.fiap.challenge.R
import br.com.fiap.challenge.database.repository.getEmailByName
import br.com.fiap.challenge.model.Email
import java.util.Calendar

@Composable
fun InboxScreen() {
    var stateEmail by remember { mutableStateOf("") }
    var listEmailByName by remember { mutableStateOf(getEmailByName(stateEmail)) }
    var selectedEmails by remember { mutableStateOf<LinkedHashSet<Email>>(linkedSetOf()) }
    var isSorted by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogText by remember { mutableStateOf("") }
    var dialogPlaceholder by remember { mutableStateOf("") }
    var currentEmailIndex by remember { mutableStateOf(-1) }

    fun showDatePicker(context: android.content.Context) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            context,
            { _, selectedYear, selectedMonth, selectedDay ->
                Toast.makeText(context, "Data Selecionada: $selectedDay/${selectedMonth + 1}/$selectedYear", Toast.LENGTH_SHORT).show()
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(color = Color(0xFF289BC4))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Da Empresa",
                    modifier = Modifier
                        .size(125.dp, 70.dp),
                    contentScale = ContentScale.Crop,
                )

                Text(
                    modifier = Modifier
                        .padding(start = 30.dp, top = 20.dp),
                    text = "Caixa GoMail",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Button(
                    onClick = { showDatePicker(context) },
                    modifier = Modifier
                        .padding(top = 13.dp, start = 40.dp)
                        .size(height = 43.dp, width = 70.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFC243444))
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Ícone de Calendário",
                        modifier = Modifier.size(23.dp)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 125.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = stateEmail,
                            onValueChange = {
                                stateEmail = it
                                listEmailByName = getEmailByName(it)
                            },
                            modifier = Modifier
                                .width(274.dp)
                                .height(82.dp)
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

                        Button(
                            onClick = {
                                isSorted = !isSorted
                                if (isSorted) {
                                    listEmailByName = listEmailByName.sortedBy { it.remetente }
                                } else {
                                    listEmailByName = getEmailByName(stateEmail)
                                }
                            },
                            modifier = Modifier
                                .padding(top = 23.dp, start = 18.dp)
                                .size(width = 73.dp, height = 45.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFC243444))
                        ) {
                            Icon(
                                imageVector = Icons.Default.List,
                                contentDescription = "Ícone de Ordenação Alfabetica",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    // Contador de linhas
                    Text(
                        text = "Total de Entradas: ${listEmailByName.size}",
                        modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                        LazyColumn(
                            modifier = Modifier
                                .height(370.dp)
                                .background(Color(0xFF5C5858))
                        ) {
                            itemsIndexed(listEmailByName) { index, email ->
                                var isImportant by remember { mutableStateOf(false) }

                                SchoolCard(
                                    email = email,
                                    isImportant = isImportant,
                                    onStarClicked = { isChecked ->
                                        isImportant = isChecked
                                    },
                                    onButtonClicked = {
                                        showDialog = true
                                        dialogText = "" // Clear previous text
                                        dialogPlaceholder = email.remetente
                                        currentEmailIndex = index
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Digite o apelido desejado",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    OutlinedTextField(
                        value = dialogText,
                        onValueChange = { dialogText = it },
                        placeholder = { Text(text = dialogPlaceholder) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = { showDialog = false },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Text("Cancelar")
                        }
                        Button(onClick = {
                            if (currentEmailIndex != -1 && dialogText.isNotBlank()) {
                                listEmailByName = listEmailByName.toMutableList().apply {
                                    this[currentEmailIndex] = this[currentEmailIndex].copy(remetente = dialogText)
                                }
                            }
                            showDialog = false
                        }) {
                            Text("Salvar")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SchoolCard(
    email: Email,
    isImportant: Boolean,
    onStarClicked: (Boolean) -> Unit,
    onButtonClicked: () -> Unit
) {
    val backgroundColor = if (isImportant) Color(0xFFF3E035) else Color(0xFF413A3A)
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
            Column {
                Text(
                    text = email.remetente,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Text(
                    text = email.endereco, // Supondo que 'endereco' seja o campo novo a ser exibido
                    fontSize = 14.sp,
                    color = textColor
                )
            }
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = email.titulo,
                fontSize = 15.sp,
                color = textColor
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = onButtonClicked
            ) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "Ícone de Editar",
                    tint = Color.White
                )
            }
        }
    }
}

@Preview
@Composable
private fun InboxScreenView() {
    InboxScreen()
}