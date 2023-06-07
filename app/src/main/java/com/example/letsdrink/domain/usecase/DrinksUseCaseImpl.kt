package com.example.letsdrink.domain.usecase

import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.Drinks
import com.example.letsdrink.domain.repository.DrinksRepository
import kotlinx.coroutines.flow.Flow

class DrinksUseCaseImpl(private val drinksRepository: DrinksRepository) : DrinksUseCase {

    override suspend fun drinksByCategory(categoryId: Long): Flow<List<Drinks>> = drinksRepository.drinksByCategory(categoryId)

    override suspend fun drinkDetail(id: Long): Flow<DrinkDetails> =
        drinksRepository.drinkDetails(id = id)


}