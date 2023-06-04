package com.example.letsdrink.data.remote.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apifactory {

    private const val URL_VIA_CEP = "https://viacep.com.br/ws/"

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(URL_VIA_CEP)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T {
        return retrofit().create(serviceClass)
    }
}
