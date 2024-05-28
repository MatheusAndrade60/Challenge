package br.com.fiap.challenge.model

import kotlin.time.TimeSource

data class Email(
    val remetente: String="",
    val titulo: String="",
    val assunto: String="",
    val data_hora: String="",
)
