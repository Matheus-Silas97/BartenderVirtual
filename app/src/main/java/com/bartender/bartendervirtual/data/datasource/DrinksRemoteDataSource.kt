package com.bartender.bartendervirtual.data.datasource

import com.bartender.bartendervirtual.domain.model.DrinkDetails
import com.bartender.bartendervirtual.domain.model.Drinks

interface DrinksRemoteDataSource {

    suspend fun drinksByCategory(categoryId: Long): List<Drinks>

    suspend fun drinkDetails(id: Long): DrinkDetails

}