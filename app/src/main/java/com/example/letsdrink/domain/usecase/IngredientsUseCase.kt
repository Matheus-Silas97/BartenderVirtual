package com.example.letsdrink.domain.usecase

import com.example.letsdrink.domain.model.Drinks
import kotlinx.coroutines.flow.Flow

interface IngredientsUseCase {

    suspend fun ingredientsDetails(ingredientName: String): Flow<List<Drinks>>

}