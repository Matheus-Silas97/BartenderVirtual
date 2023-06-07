package com.example.letsdrink.domain.usecase

import com.example.letsdrink.domain.model.Drinks
import com.example.letsdrink.domain.repository.IngredientsRepository
import kotlinx.coroutines.flow.Flow

class IngredientsUseCaseImpl(private val ingredientsRepository: IngredientsRepository) :
    IngredientsUseCase {

    override suspend fun ingredientsDetails(ingredientName: String): Flow<List<Drinks>> =
        ingredientsRepository.ingredientsDetails(ingredientName = ingredientName)

}