package br.com.fiap.challenge.database.repository

import br.com.fiap.challenge.model.Email

fun obterTodosEmails(): List<Email> {
    return listOf(
        Email("Gmail", "Ativação de conta", "Siga o nosso passo-a-passo para ativar a sua conta", "16:13"),
        Email("Amazon", "Pedido enviado", "Seu pedido foi enviado com sucesso", "15:55"),
        Email("Instagram", "Atualização de dados", "Deixe seus dados atualizados", "15:40")
    )
}
fun getEmailByName(remetente: String): List<Email> {
    return obterTodosEmails().filter {
        it.remetente.startsWith(prefix = remetente, ignoreCase = true)
    }
}