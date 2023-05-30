package com.example.letsdrink.domain.usecase

import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.Drinks
import kotlinx.coroutines.flow.Flow

interface DrinksUseCase {

    fun allDrinks(): Flow<List<Drinks>>

    fun drinkDetail(id: Long) : Flow<DrinkDetails>

}