package br.com.fiap.challenge.database.repository

import br.com.fiap.challenge.model.Email

fun obterTodosEmails(): List<Email> {
    return listOf(
        Email(1,"Gmail", "Ativação de conta", "google.com.br"),
        Email(2,"Amazon", "Pedido enviado",  "amazon.com.br"),
        Email(3,"Instagram", "Atualização de dados", "instagram.com.br"),
        Email(4,"Amanda Flores", "Novo Projeto",  "amanda.flores@gmail.com"),
        Email(5,"Juliana Barsani", "Conta a pagar",  "juliana.barsani@gmail.com")
    )
}
fun getEmailByName(remetente: String): List<Email> {
    return obterTodosEmails().filter {
        it.remetente.startsWith(prefix = remetente, ignoreCase = true)
    }
}