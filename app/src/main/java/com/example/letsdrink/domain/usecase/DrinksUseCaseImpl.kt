package com.example.letsdrink.domain.usecase

import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.Drinks
import com.example.letsdrink.domain.repository.DrinksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DrinksUseCaseImpl(private val repository: DrinksRepository) : DrinksUseCase {

    override suspend fun allDrinks(): Flow<List<Drinks>> = repository.allDrinks()

    override suspend fun drinkDetail(id: Long): Flow<DrinkDetails> =
        repository.drinkDetails(id = id)


}