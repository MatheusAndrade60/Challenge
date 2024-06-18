package br.com.fiap.challenge.database.repository

import br.com.fiap.challenge.model.Email

fun obterTodosEmails(): List<Email> {
    return listOf(
        Email(1,"Gmail", "Ativação de conta", "Siga o nosso passo-a-passo para ativar a sua conta", "google.com.br"),
        Email(2,"Amazon", "Pedido enviado", "Seu pedido foi enviado com sucesso", "amazon.com.br"),
        Email(3,"Instagram", "Atualização de dados", "Deixe seus dados atualizados", "instagram.com.br"),
        Email(4,"Amanda Flores", "Novo Projeto", "Assuntos pendentes", "amanda.flores@gmail.com")
    )
}
fun getEmailByName(remetente: String): List<Email> {
    return obterTodosEmails().filter {
        it.remetente.startsWith(prefix = remetente, ignoreCase = true)
    }
}