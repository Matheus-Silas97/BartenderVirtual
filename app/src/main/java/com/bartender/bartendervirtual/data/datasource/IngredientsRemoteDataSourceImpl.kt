package com.bartender.bartendervirtual.data.datasource

import com.bartender.bartendervirtual.data.mapper.convertIngredientsDetails
import com.bartender.bartendervirtual.data.remote.service.DrinkService
import com.bartender.bartendervirtual.domain.model.Ingredients
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IngredientsRemoteDataSourceImpl(
    private val service: DrinkService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : IngredientsRemoteDataSource {

    override suspend fun ingredientsDetails(ingredientId: Long): Ingredients =
        withContext(defaultDispatcher) {
            service.ingredientDetails(ingredientId = ingredientId).convertIngredientsDetails()
        }

}