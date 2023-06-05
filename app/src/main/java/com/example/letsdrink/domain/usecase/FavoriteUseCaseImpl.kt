package com.example.letsdrink.domain.usecase

import com.example.letsdrink.domain.model.DrinkFavorite
import com.example.letsdrink.domain.repository.FavoritesDrinksRepository
import kotlinx.coroutines.flow.Flow

class FavoriteUseCaseImpl(private val favoritesDrinksRepository: FavoritesDrinksRepository) :
    FavoriteUseCase {

    override suspend fun allDrinks(): Flow<List<DrinkFavorite>> =
        favoritesDrinksRepository.allDrinks()

    override suspend fun insertDrink(drinkFavorite: DrinkFavorite): Flow<Int> =
        favoritesDrinksRepository.insertDrink(drinkFavorite = drinkFavorite)

    override suspend fun deleteDrink(drinkFavorite: DrinkFavorite): Flow<Int> =
        favoritesDrinksRepository.deleteDrink(drinkFavorite = drinkFavorite)

}