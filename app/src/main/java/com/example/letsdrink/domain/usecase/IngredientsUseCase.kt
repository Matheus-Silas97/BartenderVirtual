package com.example.letsdrink.domain.usecase

import com.example.letsdrink.domain.model.Drinks
import com.example.letsdrink.domain.model.Ingredients
import kotlinx.coroutines.flow.Flow

interface IngredientsUseCase {

    suspend fun ingredientsDetails(ingredientId: Long): Flow<Ingredients>

}