package com.example.letsdrink.data.datasource

import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.DrinkFavorite

interface FavoritesDrinksLocalDataSource {

    suspend fun allDrinks(): List<DrinkFavorite>
    suspend fun drinkDetails(drinkId: Long): DrinkDetails
    suspend fun addDrinks(drink: DrinkDetails): Long
    suspend fun deleteDrink(drinkId: Long): Int
    suspend fun isFavorite(drinkId: Long): Boolean

}