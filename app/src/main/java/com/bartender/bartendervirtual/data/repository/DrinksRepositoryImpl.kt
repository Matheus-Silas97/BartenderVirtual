package com.bartender.bartendervirtual.data.repository

import com.bartender.bartendervirtual.data.datasource.DrinksRemoteDataSource
import com.bartender.bartendervirtual.domain.model.DrinkDetails
import com.bartender.bartendervirtual.domain.model.Drinks
import com.bartender.bartendervirtual.domain.repository.DrinksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DrinksRepositoryImpl(
    private val drinksRemoteDataSource: DrinksRemoteDataSource
) : DrinksRepository {

    override suspend fun drinksByCategory(categoryId: Long): Flow<List<Drinks>> = flow {
        emit(drinksRemoteDataSource.drinksByCategory(categoryId))
    }

    override suspend fun drinkDetails(id: Long): Flow<DrinkDetails> = flow {
        emit(drinksRemoteDataSource.drinkDetails(id = id))
    }

}