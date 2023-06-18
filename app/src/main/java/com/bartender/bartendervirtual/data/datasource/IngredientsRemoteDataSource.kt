package com.bartender.bartendervirtual.data.datasource

import com.bartender.bartendervirtual.domain.model.Ingredients

interface IngredientsRemoteDataSource {

    suspend fun ingredientsDetails(ingredientId: Long): Ingredients
}