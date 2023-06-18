package com.bartender.bartendervirtual.data.repository

import com.bartender.bartendervirtual.data.datasource.IngredientsRemoteDataSource
import com.bartender.bartendervirtual.domain.model.Ingredients
import com.bartender.bartendervirtual.domain.repository.IngredientsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IngredientsRepositoryImpl(private val ingredientsRemoteDataSource: IngredientsRemoteDataSource) :
    IngredientsRepository {

    override suspend fun ingredientsDetails(ingredientId: Long): Flow<Ingredients> = flow {
        emit(ingredientsRemoteDataSource.ingredientsDetails(ingredientId))
    }

}