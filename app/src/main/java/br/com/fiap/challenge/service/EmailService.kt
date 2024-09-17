package br.com.fiap.challenge.service

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class EmailService {

    data class EmailSent(
        val userId: String,
        val emailContentHash: String,
        val recipient: String,
        val timestamp: Long
    )

    private val emailHistory = mutableListOf<EmailSent>()
    private val userEmailCounts = ConcurrentHashMap<String, MutableList<Long>>()
    private val emailRateLimit = 35 // e-mails permitidos por período
    private val timeWindowMillis = 3600000 // 1 hora em milissegundos

    // Função para verificar se o comportamento é de spam
    fun isSpam(userId: String, emailContent: String, recipient: String, timestamp: Long): Boolean {
        val hash = emailContent.hashCode().toString() // Hash do conteúdo do e-mail
        val recentEmails = emailHistory.filter { it.userId == userId && it.timestamp > timestamp - 60000 } // Último minuto

        return recentEmails.count { it.emailContentHash == hash } > 5
    }

    // Função para verificar se o conteúdo é suspeito
    fun isSuspiciousContent(emailContent: String): Boolean {
        val spamKeywords = listOf("gratis", "oferta", "faça dinheiro", "ganhe", "desconto", "clique aqui")
        return spamKeywords.any { keyword -> emailContent.contains(keyword, ignoreCase = true) }
    }

    // Registrar envio de e-mails
    fun logEmailSent(userId: String, emailContent: String, recipient: String, timestamp: Long) {
        val emailSent = EmailSent(userId, emailContent.hashCode().toString(), recipient, timestamp)
        emailHistory.add(emailSent)

        // Adicionar à contagem de e-mails do usuário
        userEmailCounts.computeIfAbsent(userId) { mutableListOf() }.add(timestamp)

        // Limitar e-mails por usuário
        val timestamps = userEmailCounts[userId] ?: return
        val cutoffTime = timestamp - timeWindowMillis
        userEmailCounts[userId] = timestamps.filter { it > cutoffTime }.toMutableList()

        if (userEmailCounts[userId]!!.size > emailRateLimit) {
            println("Limite de envio de e-mails excedido para o usuário $userId")
            throw RateLimitExceededException("Você excedeu o limite de envio de e-mails")
        }
    }

    class RateLimitExceededException(message: String) : Exception(message)
}