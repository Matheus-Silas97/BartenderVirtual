package com.example.letsdrink.data.remote.service

import com.example.letsdrink.data.remote.response.CategoryResponse
import com.example.letsdrink.data.remote.response.DrinkDetailsResponse
import com.example.letsdrink.data.remote.response.DrinkResponse
import retrofit2.http.GET

interface DrinkService {

    @GET("")
    suspend fun drinksByCategory(categoryId: Long): List<DrinkResponse>

    @GET("")
    suspend fun getOneDrink(id:Long): DrinkDetailsResponse

    @GET("/categories.json")
    suspend fun getCategories(): List<CategoryResponse>?

}