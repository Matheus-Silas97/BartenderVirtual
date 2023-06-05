package com.example.letsdrink.data.datasource

import com.example.letsdrink.data.local.AppDatabase
import com.example.letsdrink.data.mapper.convertFavorite
import com.example.letsdrink.data.mapper.convertToDrinkFavoriteEntity
import com.example.letsdrink.domain.model.DrinkFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoritesDrinksLocalDataSourceImpl(
    private val appDatabase: AppDatabase
) : FavoritesDrinksLocalDataSource {
    override suspend fun allDrinks(): List<DrinkFavorite> = withContext(Dispatchers.IO) {
        return@withContext appDatabase.favorites().getAll().convertFavorite()
    }

    override suspend fun addDrinks(drink: DrinkFavorite): Int = withContext(Dispatchers.IO) {
        return@withContext appDatabase.favorites().insert(drink.convertToDrinkFavoriteEntity())
    }

    override suspend fun deleteDrink(drink: DrinkFavorite): Int = withContext(Dispatchers.IO) {
        return@withContext appDatabase.favorites()
            .deleteTraining(drink.convertToDrinkFavoriteEntity())
    }
}