package com.example.letsdrink.data.datasource

import com.example.letsdrink.data.mapper.convertDrinkDetailsEntity
import com.example.letsdrink.data.mapper.convertDrinkListEntity
import com.example.letsdrink.data.remote.service.DrinkService
import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.Drinks
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