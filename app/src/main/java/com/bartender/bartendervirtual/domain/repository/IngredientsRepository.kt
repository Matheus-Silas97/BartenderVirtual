package com.bartender.bartendervirtual.domain.repository

import com.bartender.bartendervirtual.domain.model.Ingredients
import kotlinx.coroutines.flow.Flow

interface IngredientsRepository {

    suspend fun ingredientsDetails(ingredientId: Long): Flow<Ingredients>

}