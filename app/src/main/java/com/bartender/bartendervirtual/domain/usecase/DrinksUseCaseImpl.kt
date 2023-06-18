package com.bartender.bartendervirtual.domain.usecase

import com.bartender.bartendervirtual.domain.model.DrinkDetails
import com.bartender.bartendervirtual.domain.model.Drinks
import com.bartender.bartendervirtual.domain.repository.DrinksRepository
import kotlinx.coroutines.flow.Flow

class DrinksUseCaseImpl(private val drinksRepository: DrinksRepository) : DrinksUseCase {

    override suspend fun drinksByCategory(categoryId: Long): Flow<List<Drinks>> = drinksRepository.drinksByCategory(categoryId)

    override suspend fun drinkDetail(id: Long): Flow<DrinkDetails> =
        drinksRepository.drinkDetails(id = id)


}