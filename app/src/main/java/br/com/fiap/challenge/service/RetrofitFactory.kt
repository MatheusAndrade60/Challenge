package br.com.fiap.challenge.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    private val BASE_URL_CEP = "https://viacep.com.br/ws/"
    private val BASE_URL_EMAIL = "http://localhost:8080/" // URL do seu mock de e-mail

    private val retrofitCep = Retrofit.Builder()
        .baseUrl(BASE_URL_CEP)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitEmail = Retrofit.Builder()
        .baseUrl(BASE_URL_EMAIL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCepService(): EnderecoService {
        return retrofitCep.create(EnderecoService::class.java)
    }

    fun getEmailService(): EmailApiService {
        return retrofitEmail.create(EmailApiService::class.java)
    }
}