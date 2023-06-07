package com.example.letsdrink.domain.repository

import com.example.letsdrink.domain.model.Drinks
import kotlinx.coroutines.flow.Flow

interface IngredientsRepository {

    suspend fun ingredientsDetails(ingredientName: String): Flow<List<Drinks>>

}