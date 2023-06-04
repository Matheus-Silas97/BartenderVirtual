package com.example.letsdrink.domain.usecase

import com.example.letsdrink.domain.model.Category
import com.example.letsdrink.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryUseCaseImpl(private val categoryRepository: CategoryRepository) : CategoryUseCase {

    override suspend fun categories(): Flow<List<Category>> = categoryRepository.categories()

}