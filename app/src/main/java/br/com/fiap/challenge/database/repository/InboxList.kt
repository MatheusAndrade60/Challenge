package br.com.fiap.challenge.database.repository

import br.com.fiap.challenge.model.Email
import kotlin.time.TimeSource

fun obterTodosEmails(): List<Email> {
    return listOf(
        Email("Gmail", "Ativação de conta", "Siga o nosso passo-a-passo para ativat a sua conta", "15:40"),
        Email("WhatsApp", "Ativação de conta", "Siga o nosso passo-a-passo para ativat a sua conta", "15:40")
    )
}
fun getEmailByName(remetente: String): List<Email> {
    return obterTodosEmails().filter {
        it.remetente.startsWith(prefix = remetente, ignoreCase = true)
    }
}