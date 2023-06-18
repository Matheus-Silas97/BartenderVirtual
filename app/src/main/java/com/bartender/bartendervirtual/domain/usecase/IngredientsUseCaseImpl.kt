package com.bartender.bartendervirtual.domain.usecase

import com.bartender.bartendervirtual.domain.model.Ingredients
import com.bartender.bartendervirtual.domain.repository.IngredientsRepository
import kotlinx.coroutines.flow.Flow

class IngredientsUseCaseImpl(private val ingredientsRepository: IngredientsRepository) :
    IngredientsUseCase {

    override suspend fun ingredientsDetails(ingredientId: Long): Flow<Ingredients> =
        ingredientsRepository.ingredientsDetails(ingredientId = ingredientId)

}