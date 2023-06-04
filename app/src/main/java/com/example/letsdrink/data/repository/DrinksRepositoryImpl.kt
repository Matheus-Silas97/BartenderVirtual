package com.example.letsdrink.data.repository

import com.example.letsdrink.data.datasource.DrinksRemoteDataSource
import com.example.letsdrink.data.mapper.toDrinkDetailsEntity
import com.example.letsdrink.data.mapper.toDrinkListEntity
import com.example.letsdrink.data.remote.service.DrinkService
import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.Drinks
import com.example.letsdrink.domain.repository.DrinksRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

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