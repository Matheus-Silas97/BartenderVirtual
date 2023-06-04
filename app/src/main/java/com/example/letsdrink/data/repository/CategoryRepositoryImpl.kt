package com.example.letsdrink.data.repository

import com.example.letsdrink.data.datasource.CategoryRemoteDataSource
import com.example.letsdrink.domain.model.Category
import com.example.letsdrink.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryRepositoryImpl(private val categoryRemoteDataSource : CategoryRemoteDataSource): CategoryRepository {

    override suspend fun categories(): Flow<List<Category>> = flow {
        emit(categoryRemoteDataSource.categories())

    }
}