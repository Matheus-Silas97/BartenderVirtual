package com.example.letsdrink.domain.repository

import com.example.letsdrink.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun categories(): Flow<List<Category>>

}