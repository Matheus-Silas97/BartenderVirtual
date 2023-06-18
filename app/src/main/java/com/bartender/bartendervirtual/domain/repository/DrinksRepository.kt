package com.bartender.bartendervirtual.domain.repository

import com.bartender.bartendervirtual.domain.model.DrinkDetails
import com.bartender.bartendervirtual.domain.model.Drinks
import kotlinx.coroutines.flow.Flow

interface DrinksRepository {

    suspend fun drinksByCategory(categoryId: Long): Flow<List<Drinks>>

    suspend fun drinkDetails(id: Long): Flow<DrinkDetails>

}