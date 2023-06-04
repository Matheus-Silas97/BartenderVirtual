package com.example.letsdrink.data.datasource

import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.Drinks

interface DrinksRemoteDataSource {

    suspend fun drinksByCategory(categoryId: Long): List<Drinks>

    suspend fun drinkDetails(id: Long): DrinkDetails

}