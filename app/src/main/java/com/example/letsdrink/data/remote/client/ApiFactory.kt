package com.example.letsdrink.data.remote.client

import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Apifactory {

    private const val BASE_URL = "https://rails-production-72ec.up.railway.app/"


    private fun retrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = Builder()
            .addInterceptor(logging)
            .build()


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun <T> create(serviceClass: Class<T>): T {
        return retrofit().create(serviceClass)
    }
}
