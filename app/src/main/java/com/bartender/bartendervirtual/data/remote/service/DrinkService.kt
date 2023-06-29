package com.bartender.bartendervirtual.data.remote.service

import com.bartender.bartendervirtual.data.remote.response.CategoryResponse
import com.bartender.bartendervirtual.data.remote.response.DrinkDetailsResponse
import com.bartender.bartendervirtual.data.remote.response.DrinkResponse
import com.bartender.bartendervirtual.data.remote.response.IngredientsDetailsResponse
import com.bartender.bartendervirtual.data.remote.response.RecommendationsResponse
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

    @GET("ingredients/{ingredient_id}.json")
    suspend fun ingredientDetails(@Path("ingredient_id") ingredientId: Long): IngredientsDetailsResponse

    @GET("")
    suspend fun recommendations(): RecommendationsResponse


}