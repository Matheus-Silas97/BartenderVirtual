package com.bartender.bartendervirtual.domain.usecase

import com.bartender.bartendervirtual.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryUseCase {

    suspend fun categories(): Flow<List<Category>>

}