package com.example.letsdrink.data.repository

import com.example.letsdrink.data.datasource.IngredientsRemoteDataSource
import com.example.letsdrink.domain.model.Drinks
import com.example.letsdrink.domain.repository.IngredientsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IngredientsRepositoryImpl(private val ingredientsRemoteDataSource: IngredientsRemoteDataSource) :
    IngredientsRepository {

    override suspend fun ingredientsDetails(ingredientName: String): Flow<List<Drinks>> = flow {
        emit(ingredientsRemoteDataSource.ingredientsDetails(ingredientName))
    }

}