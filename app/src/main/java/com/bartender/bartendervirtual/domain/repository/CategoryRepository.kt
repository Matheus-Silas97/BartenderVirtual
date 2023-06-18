package com.bartender.bartendervirtual.domain.repository

import com.bartender.bartendervirtual.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun categories(): Flow<List<Category>>

}