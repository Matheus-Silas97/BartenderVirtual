package com.example.letsdrink.domain.repository

import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.Drinks
import kotlinx.coroutines.flow.Flow

interface DrinksRepository {

    suspend fun drinksByCategory(categoryId: Long): Flow<List<Drinks>>

    suspend fun drinkDetails(id: Long): Flow<DrinkDetails>

}