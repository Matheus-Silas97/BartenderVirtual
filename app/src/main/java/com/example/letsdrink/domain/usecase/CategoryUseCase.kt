package com.example.letsdrink.domain.usecase

import com.example.letsdrink.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryUseCase {

    suspend fun categories(): Flow<List<Category>>

}