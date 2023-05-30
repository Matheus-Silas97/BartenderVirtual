package com.example.letsdrink.domain.repository

import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.Drinks

interface DrinksRepository {

    suspend fun allDrinks(): List<Drinks>

    suspend fun drinkDetails(id: Long): DrinkDetails

}