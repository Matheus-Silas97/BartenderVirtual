package com.example.letsdrink.domain.usecase

import com.example.letsdrink.domain.model.Ingredients
import com.example.letsdrink.domain.repository.IngredientsRepository
import kotlinx.coroutines.flow.Flow

class IngredientsUseCaseImpl(private val ingredientsRepository: IngredientsRepository) :
    IngredientsUseCase {

    override suspend fun ingredientsDetails(ingredientId: Long): Flow<Ingredients> =
        ingredientsRepository.ingredientsDetails(ingredientId = ingredientId)

}