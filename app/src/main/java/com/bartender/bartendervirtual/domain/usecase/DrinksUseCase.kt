package com.bartender.bartendervirtual.domain.usecase

import com.bartender.bartendervirtual.domain.model.DrinkDetails
import com.bartender.bartendervirtual.domain.model.Drinks
import kotlinx.coroutines.flow.Flow

interface DrinksUseCase {

    suspend fun drinksByCategory(categoryId: Long): Flow<List<Drinks>>

    suspend fun drinkDetail(id: Long) : Flow<DrinkDetails>

}