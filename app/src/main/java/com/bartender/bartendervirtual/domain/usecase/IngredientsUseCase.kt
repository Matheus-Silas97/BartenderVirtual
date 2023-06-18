package com.bartender.bartendervirtual.domain.usecase

import com.bartender.bartendervirtual.domain.model.Ingredients
import kotlinx.coroutines.flow.Flow

interface IngredientsUseCase {

    suspend fun ingredientsDetails(ingredientId: Long): Flow<Ingredients>

}