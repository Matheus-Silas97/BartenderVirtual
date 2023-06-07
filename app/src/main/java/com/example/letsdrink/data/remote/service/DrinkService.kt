package com.example.letsdrink.data.remote.service

import com.example.letsdrink.data.remote.response.CategoryResponse
import com.example.letsdrink.data.remote.response.DrinkDetailsResponse
import com.example.letsdrink.data.remote.response.DrinkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DrinkService {

    @GET("categories.json")
    suspend fun categories(): List<CategoryResponse>?

    @GET("drinks.json")
    suspend fun drinksByCategory(@Query("category") categoryId: Long): List<DrinkResponse>

    @GET("/drinks/{drink_id}.json")
    suspend fun drinkDetails(@Path("drink_id") id: Long): DrinkDetailsResponse

    @GET("drinks.json?")
    suspend fun drinksByIngredient(@Query("ingredient_names") ingredientName: String): List<DrinkResponse>


}