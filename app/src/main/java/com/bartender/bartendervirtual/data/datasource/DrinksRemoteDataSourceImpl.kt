package com.bartender.bartendervirtual.data.datasource

import com.bartender.bartendervirtual.data.mapper.convertDrinkDetailsEntity
import com.bartender.bartendervirtual.data.mapper.convertDrinkListEntity
import com.bartender.bartendervirtual.data.remote.service.DrinkService
import com.bartender.bartendervirtual.domain.model.DrinkDetails
import com.bartender.bartendervirtual.domain.model.Drinks
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DrinksRemoteDataSourceImpl(
    private val service: DrinkService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : DrinksRemoteDataSource {
    override suspend fun drinksByCategory(categoryId: Long): List<Drinks> =
        withContext(defaultDispatcher) {
            service.drinksByCategory(categoryId).convertDrinkListEntity()
        }

    override suspend fun drinkDetails(id: Long): DrinkDetails =
        withContext(defaultDispatcher) {
            service.drinkDetails(id = id).convertDrinkDetailsEntity()
        }
}