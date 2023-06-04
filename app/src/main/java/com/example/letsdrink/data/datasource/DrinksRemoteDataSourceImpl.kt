package com.example.letsdrink.data.datasource

import com.example.letsdrink.data.mapper.toDrinkDetailsEntity
import com.example.letsdrink.data.mapper.toDrinkListEntity
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
    override suspend fun allDrinks(): List<Drinks> =
        withContext(defaultDispatcher) {
            service.allDrinks().toDrinkListEntity()
        }

    override suspend fun drinkDetails(id: Long): DrinkDetails =
        withContext(defaultDispatcher) {
            service.getOneDrink(id = id).toDrinkDetailsEntity()
        }
}