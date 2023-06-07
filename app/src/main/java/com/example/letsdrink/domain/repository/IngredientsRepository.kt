package com.example.letsdrink.domain.repository

import com.example.letsdrink.domain.model.Ingredients
import kotlinx.coroutines.flow.Flow

interface IngredientsRepository {

    suspend fun ingredientsDetails(ingredientId: Long): Flow<Ingredients>

}