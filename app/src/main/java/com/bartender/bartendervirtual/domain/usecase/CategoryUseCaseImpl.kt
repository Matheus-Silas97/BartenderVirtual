package com.bartender.bartendervirtual.domain.usecase

import com.bartender.bartendervirtual.domain.model.Category
import com.bartender.bartendervirtual.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryUseCaseImpl(private val categoryRepository: CategoryRepository) : CategoryUseCase {

    override suspend fun categories(): Flow<List<Category>> = categoryRepository.categories()

}