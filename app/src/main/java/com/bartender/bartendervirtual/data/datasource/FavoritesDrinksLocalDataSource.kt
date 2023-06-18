package com.bartender.bartendervirtual.data.datasource

import com.bartender.bartendervirtual.domain.model.DrinkDetails
import com.bartender.bartendervirtual.domain.model.DrinkFavorite

interface FavoritesDrinksLocalDataSource {

    suspend fun allDrinks(): List<DrinkFavorite>
    suspend fun drinkDetails(drinkId: Long): DrinkDetails
    suspend fun addDrinks(drink: DrinkDetails): Long
    suspend fun deleteDrink(drinkId: Long): Int
    suspend fun isFavorite(drinkId: Long): Boolean

}