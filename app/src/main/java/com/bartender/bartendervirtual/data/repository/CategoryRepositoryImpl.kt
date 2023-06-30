package com.bartender.bartendervirtual.data.repository

import com.bartender.bartendervirtual.data.datasource.CategoryRemoteDataSource
import com.bartender.bartendervirtual.domain.model.Category
import com.bartender.bartendervirtual.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryRepositoryImpl(private val categoryRemoteDataSource : CategoryRemoteDataSource): CategoryRepository {

    override suspend fun categories(): Flow<List<Category>> = flow {
        emit(categoryRemoteDataSource.categories())
    }

}