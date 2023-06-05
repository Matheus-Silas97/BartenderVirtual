package com.example.letsdrink.domain.repository

import com.example.letsdrink.domain.model.DrinkFavorite
import kotlinx.coroutines.flow.Flow

interface FavoritesDrinksRepository {

    suspend fun allDrinks(): Flow<List<DrinkFavorite>>

    suspend fun insertDrink(drinkFavorite: DrinkFavorite): Flow<Int>

    suspend fun deleteDrink(drinkFavorite: DrinkFavorite): Flow<Int>

}