package com.example.letsdrink.data.datasource

import com.example.letsdrink.domain.model.Drinks
import com.example.letsdrink.domain.model.Ingredients

interface IngredientsRemoteDataSource {

    suspend fun ingredientsDetails(ingredientId: Long): Ingredients
}