package com.example.letsdrink.domain.usecase

import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.Drinks
import kotlinx.coroutines.flow.Flow

interface DrinksUseCase {

    suspend fun drinksByCategory(categoryId: Long): Flow<List<Drinks>>

    suspend fun drinkDetail(id: Long) : Flow<DrinkDetails>

}