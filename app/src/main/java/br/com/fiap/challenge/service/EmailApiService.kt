package br.com.fiap.challenge.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import br.com.fiap.challenge.model.Email

interface EmailApiService {
    @GET("emails")
    fun getEmails(): Call<List<Email>>

    @GET("emails/{id}")
    fun getEmail(@Path("id") id: Int): Call<Email>

    @POST("emails/send")
    fun sendEmail(@Body email: Email): Call<Email>

    @POST("calendar/invite")
    fun inviteToCalendar(@Body invite: CalendarInvite): Call<Void>
}

data class Email(
    val id: Int,
    val sender: String,
    val subject: String,
    val address: String
)

data class CalendarInvite(
    val email: String,
    val event: String,
    val dateTime: String
)