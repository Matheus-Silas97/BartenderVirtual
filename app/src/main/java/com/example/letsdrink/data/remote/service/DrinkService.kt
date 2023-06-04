package com.example.letsdrink.data.remote.service

import com.example.letsdrink.data.remote.response.DrinkDetailsResponse
import com.example.letsdrink.data.remote.response.DrinkResponse
import retrofit2.http.GET

interface DrinkService {

    @GET("")
    suspend fun allDrinks(): List<DrinkResponse>

    @GET("")
    suspend fun getOneDrink(id:Long): DrinkDetailsResponse

}