package br.com.fiap.challenge.service

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.Request
import okio.Timeout
import br.com.fiap.challenge.model.Email


// Uma classe simples para criar uma resposta mockada
class MockCall<T>(private val response: Response<T>) : Call<T> {
    override fun enqueue(callback: Callback<T>) {
        callback.onResponse(this, response)
    }

    override fun isExecuted(): Boolean = false

    override fun clone(): Call<T> = MockCall(response)

    override fun isCanceled(): Boolean = false

    override fun cancel() {}

    override fun request(): Request = Request.Builder().build()

    override fun timeout(): Timeout = Timeout.NONE

    override fun execute(): Response<T> = response
}

class MockEmailApiService : EmailApiService {
    private val emails = mutableListOf(
        Email(1, "Gmail", "Account Activation", "google.com.br"),
        Email(2, "Amazon", "Order Shipped", "amazon.com.br")
    )

    override fun getEmails(): Call<List<Email>> {
        return MockCall(Response.success(emails))
    }

    override fun getEmail(id: Int): Call<Email> {
        val email = emails.find { it.id == id }
        return MockCall(Response.success(email))
    }

    override fun sendEmail(email: Email): Call<Email> {
        val newEmail = email.copy(id = (emails.maxOfOrNull { it.id } ?: 0) + 1)
        emails.add(newEmail)
        return MockCall(Response.success(newEmail))
    }

    override fun inviteToCalendar(invite: CalendarInvite): Call<Void> {
        return MockCall(Response.success(null))
    }
}