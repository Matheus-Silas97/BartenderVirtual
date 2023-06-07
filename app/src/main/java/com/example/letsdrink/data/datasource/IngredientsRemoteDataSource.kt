package com.example.letsdrink.data.datasource

import com.example.letsdrink.domain.model.Drinks

interface IngredientsRemoteDataSource {

    suspend fun ingredientsDetails(ingredientName: String): List<Drinks>
}