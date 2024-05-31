package br.com.fiap.challenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TB_USUARIOS")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    var id_usuario : Long = 0,
    var nome : String = "",
    var cpf : String = "",
    var cep : String = "",
    var rua : String = "",
    var cidade : String = "",
    var bairro : String = "",
    var uf : String = "",
    var email : String = "",
    var senha : String = "",
)