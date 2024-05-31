package br.com.fiap.challenge.screens

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.challenge.R
import br.com.fiap.challenge.component.Header
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import br.com.fiap.challenge.database.repository.UsuarioRepository
import br.com.fiap.challenge.model.Endereco
import br.com.fiap.challenge.model.Usuario
import br.com.fiap.challenge.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CadastroScreen(navController: NavController) {
    var nome by remember {
        mutableStateOf("")
    }
    var cpf by remember {
        mutableStateOf("")
    }
    var cep by remember {
        mutableStateOf("")
    }
    var cepState by remember {
        mutableStateOf(Endereco())
    }
    var email by remember {
        mutableStateOf("")
    }
    var senha by remember {
        mutableStateOf("")
    }

    val canNavigate = nome.isNotBlank() &&
            cpf.isNotBlank() &&
            cep.isNotBlank() &&
            email.isNotBlank() &&
            senha.isNotBlank()

    //Box inicial com logo e titulo
    Box(
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White),
        ) {
            item {
                //Medoto que ira retornar um layout para o Header
                Header()
            }
            item() {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.White),
                ) {
                    //Card com o login do usuario
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp, vertical = 42.dp),
                    ) {
                        val context = LocalContext.current
                        val usuarioRepository = UsuarioRepository(context)

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .offset(y = (40).dp),
                            colors = CardDefaults.cardColors(Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(
                                        vertical = 16.dp,
                                        horizontal = 32.dp
                                    )
                            ) {
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = "Cadastro Webmail",
                                    modifier = Modifier.fillMaxWidth(),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center
                                )

                                Spacer(modifier = Modifier.height(15.dp))
                                OutlinedTextField(
                                    value = nome,
                                    onValueChange = {
                                        nome = it
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(height = 50.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedBorderColor = colorResource(id = R.color.gray),
                                        focusedBorderColor = colorResource(id = R.color.black)
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    placeholder = {
                                        Text(text = "Nome")
                                    }
                                )

                                Spacer(modifier = Modifier.height(10.dp))
                                OutlinedTextField(
                                    value = cpf,
                                    onValueChange = {
                                        if (it.length <= 11) {
                                            cpf = it
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(height = 50.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedBorderColor = colorResource(id = R.color.gray),
                                        focusedBorderColor = colorResource(id = R.color.black)
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    placeholder = {
                                        Text(text = "CPF")
                                    }
                                )
                                Text(
                                    text = "Somente números. Ex:012234",
                                    modifier = Modifier
                                        .padding(start = 2.dp),
                                    fontSize = 11.sp,
                                    color = Color.Gray
                                )

                                Spacer(modifier = Modifier.height(10.dp))
                                OutlinedTextField(
                                    value = cep,
                                    onValueChange = {
                                        if (it.length <= 8) {
                                            cep = it
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(height = 50.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedBorderColor = colorResource(id = R.color.gray),
                                        focusedBorderColor = colorResource(id = R.color.black)
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    placeholder = {
                                        Text(text = "CEP")
                                    },
                                    trailingIcon = {
                                        IconButton(onClick = {
                                            val call = RetrofitFactory().getCepService()
                                                .getEnderecoByCep(cep = cep)
                                            call.enqueue(object : Callback<Endereco> {
                                                override fun onResponse(
                                                    call: Call<Endereco>,
                                                    response: Response<Endereco>
                                                ) {
                                                    cepState = response.body()!!
                                                }

                                                override fun onFailure(
                                                    call: Call<Endereco>,
                                                    t: Throwable
                                                ) {
                                                    Log.i("API", "onResponse: ${t.message}")
                                                }
                                            })
                                        }) {
                                            Icon(
                                                imageVector = Icons.Default.Search,
                                                contentDescription = "icon_search"
                                            )
                                        }
                                    }
                                )
                                Text(
                                    text = "Somente números. Ex:0234113",
                                    modifier = Modifier
                                        .padding(start = 2.dp),
                                    fontSize = 11.sp,
                                    color = Color.Gray
                                )

                                Spacer(modifier = Modifier.height(10.dp))
                                OutlinedTextField(
                                    value = cepState.rua,
                                    onValueChange = {

                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(height = 50.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedBorderColor = colorResource(id = R.color.gray),
                                        focusedBorderColor = colorResource(id = R.color.black)
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    placeholder = {
                                        Text(text = "Rua")
                                    }
                                )

                                Spacer(modifier = Modifier.height(10.dp))
                                OutlinedTextField(
                                    value = cepState.cidade,
                                    onValueChange = {},
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(height = 50.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedBorderColor = colorResource(id = R.color.gray),
                                        focusedBorderColor = colorResource(id = R.color.black)
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    placeholder = {
                                        Text(text = "Cidade")
                                    }
                                )

                                Spacer(modifier = Modifier.height(10.dp))
                                OutlinedTextField(
                                    value = cepState.bairro,
                                    onValueChange = {},
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(height = 50.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedBorderColor = colorResource(id = R.color.gray),
                                        focusedBorderColor = colorResource(id = R.color.black)
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    placeholder = {
                                        Text(text = "Bairro")
                                    }
                                )

                                Spacer(modifier = Modifier.height(10.dp))
                                OutlinedTextField(
                                    value = cepState.uf,
                                    onValueChange = {
                                        if (it.length <= 2) {
                                            cep = it
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(height = 50.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedBorderColor = colorResource(id = R.color.gray),
                                        focusedBorderColor = colorResource(id = R.color.black)
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    placeholder = {
                                        Text(text = "UF")
                                    }
                                )

                                Spacer(modifier = Modifier.height(10.dp))
                                OutlinedTextField(
                                    value = email,
                                    onValueChange = {
                                        email = it
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(height = 50.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedBorderColor = colorResource(id = R.color.gray),
                                        focusedBorderColor = colorResource(id = R.color.black)
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                    placeholder = {
                                        Text(text = "Email")
                                    }
                                )

                                Spacer(modifier = Modifier.height(10.dp))
                                OutlinedTextField(
                                    value = senha,
                                    onValueChange = {
                                        if (it.length <= 6) {
                                            senha = it
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(height = 50.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedBorderColor = colorResource(id = R.color.gray),
                                        focusedBorderColor = colorResource(id = R.color.black)
                                    ),
                                    placeholder = {
                                        Text(text = "Senha")
                                    },
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        imeAction = ImeAction.Done,
                                        keyboardType = KeyboardType.Number
                                    ),

                                    visualTransformation = PasswordVisualTransformation()
                                )
                                Text(
                                    text = "No máximo 6 números",
                                    modifier = Modifier
                                        .padding(start = 20.dp),
                                    fontSize = 11.sp,
                                    color = Color.Gray
                                )

                                //Botão que ir logar o usuario e levalo para a tela da utilização da API
                                Spacer(modifier = Modifier.height(5.dp))
                                Button(
                                    onClick = {
                                        val usuario = Usuario(
                                            id_usuario = 0,
                                            nome = nome,
                                            cpf = cpf,
                                            cep = cep,
                                            rua = cepState.rua,
                                            cidade = cepState.cidade,
                                            bairro = cepState.bairro,
                                            uf = cepState.uf,
                                            email = email,
                                            senha = senha
                                        )
                                        usuarioRepository.salvar(usuario)
                                        navController.navigate("login")
                                    },
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .size(width = 303.dp, height = 40.dp)
                                        .fillMaxWidth(),
                                    shape = RoundedCornerShape(5.dp),
                                    colors = ButtonDefaults.buttonColors(Color(0xFFD50D3D)),
                                    enabled = canNavigate
                                ) {
                                    Text(
                                        text = "Cadastrar-se",
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//private fun Preview() {
//    CadastroScreen()
//}
