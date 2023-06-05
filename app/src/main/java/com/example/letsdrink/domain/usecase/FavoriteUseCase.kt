package com.example.letsdrink.domain.usecase

import com.example.letsdrink.domain.model.DrinkFavorite
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {

    suspend fun allDrinks(): Flow<List<DrinkFavorite>>

    suspend fun insertDrink(drinkFavorite: DrinkFavorite): Flow<Int>

    suspend fun deleteDrink(drinkFavorite: DrinkFavorite): Flow<Int>

}