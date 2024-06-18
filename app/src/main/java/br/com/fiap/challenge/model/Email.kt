package br.com.fiap.challenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.TimeSource
@Entity(tableName = "TB_EMAILS")
data class Email(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var remetente: String="",
    val titulo: String="",
    val assunto: String="",
    val endereco: String=""
)