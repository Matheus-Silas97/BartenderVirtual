package com.example.letsdrink.data.repository

import com.example.letsdrink.data.datasource.FavoritesDrinksLocalDataSource
import com.example.letsdrink.domain.model.DrinkFavorite
import com.example.letsdrink.domain.repository.FavoritesDrinksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoritesDrinksRepositoryImpl(private val favoritesDrinksLocalDataSource: FavoritesDrinksLocalDataSource) :
    FavoritesDrinksRepository {

    override suspend fun allDrinks(): Flow<List<DrinkFavorite>> = flow {
        emit(favoritesDrinksLocalDataSource.allDrinks())
    }

    override suspend fun insertDrink(drinkFavorite: DrinkFavorite): Flow<Int> = flow {
        emit(favoritesDrinksLocalDataSource.addDrinks(drink = drinkFavorite))
    }

    override suspend fun deleteDrink(drinkFavorite: DrinkFavorite): Flow<Int> = flow {
        emit(favoritesDrinksLocalDataSource.deleteDrink(drink = drinkFavorite))
    }
}