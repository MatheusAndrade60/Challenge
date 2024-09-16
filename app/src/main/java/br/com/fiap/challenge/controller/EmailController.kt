package br.com.fiap.challenge.controller

import br.com.fiap.challenge.service.EmailService

class EmailController {

    private var emailService = EmailService()

    fun sendEmail(userId: String, emailContent: String, recipient: String) {
        val timestamp = System.currentTimeMillis()

        try {
            // Verificar se é spam
            if (emailService.isSpam(userId, emailContent, recipient, timestamp)) {
                println("Comportamento de spam detectado. E-mail não enviado.")
                return
            }

            // Verificar conteúdo suspeito
            if (emailService.isSuspiciousContent(emailContent)) {
                println("Conteúdo do e-mail considerado suspeito. E-mail não enviado.")
                return
            }

            // Registrar o envio e verificar limite de taxa
            emailService.logEmailSent(userId, emailContent, recipient, timestamp)

            // Enviar o e-mail
            println("E-mail enviado para $recipient")

        } catch (e: EmailService.RateLimitExceededException) {
            println(e.message)
        }
    }
}