package com.example.letsdrink.data.datasource

import com.example.letsdrink.domain.model.DrinkFavorite

interface FavoritesDrinksLocalDataSource {

    suspend fun allDrinks(): List<DrinkFavorite>
    suspend fun addDrinks(drink: DrinkFavorite): Int
    suspend fun deleteDrink(drink: DrinkFavorite): Int

}