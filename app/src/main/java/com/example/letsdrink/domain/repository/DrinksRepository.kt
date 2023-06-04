package com.example.letsdrink.domain.repository

import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.Drinks
import kotlinx.coroutines.flow.Flow

interface DrinksRepository {

    suspend fun allDrinks(): Flow<List<Drinks>>

    suspend fun drinkDetails(id: Long): Flow<DrinkDetails>

}