package com.example.letsdrink.data.datasource

import com.example.letsdrink.data.mapper.convertDrinkListEntity
import com.example.letsdrink.data.remote.service.DrinkService
import com.example.letsdrink.domain.model.Drinks
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IngredientsRemoteDataSourceImpl(
    private val service: DrinkService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : IngredientsRemoteDataSource {

    override suspend fun ingredientsDetails(ingredientName: String): List<Drinks> =
        withContext(defaultDispatcher) {
            service.drinksByIngredient(ingredientName = ingredientName).convertDrinkListEntity()
        }

}