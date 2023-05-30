package com.example.letsdrink.domain.usecase

import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.Drinks
import com.example.letsdrink.domain.repository.DrinksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DrinksUseCaseImpl(private val repository: DrinksRepository) : DrinksUseCase {

    override fun allDrinks(): Flow<List<Drinks>> = flow {
        emit(repository.allDrinks())
    }

    override fun drinkDetail(id: Long): Flow<DrinkDetails> = flow {
        emit(repository.drinkDetails(id = id))
    }


}