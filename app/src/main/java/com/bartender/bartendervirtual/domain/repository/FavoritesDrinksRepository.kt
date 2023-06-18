package com.bartender.bartendervirtual.domain.repository

import com.bartender.bartendervirtual.domain.model.DrinkDetails
import com.bartender.bartendervirtual.domain.model.DrinkFavorite
import kotlinx.coroutines.flow.Flow

interface FavoritesDrinksRepository {

    suspend fun allDrinks(): Flow<List<DrinkFavorite>>
    suspend fun drinkDetails(drinkId: Long): Flow<DrinkDetails>

    suspend fun insertDrink(drinkFavorite: DrinkDetails): Flow<Long>

    suspend fun deleteDrink(drinkId: Long): Flow<Int>

    suspend fun isFavorite(drinkId: Long): Flow<Boolean>

}