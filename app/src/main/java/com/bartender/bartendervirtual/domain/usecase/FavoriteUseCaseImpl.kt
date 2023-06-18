package com.bartender.bartendervirtual.domain.usecase

import com.bartender.bartendervirtual.domain.model.DrinkDetails
import com.bartender.bartendervirtual.domain.model.DrinkFavorite
import com.bartender.bartendervirtual.domain.repository.FavoritesDrinksRepository
import kotlinx.coroutines.flow.Flow

class FavoriteUseCaseImpl(private val favoritesDrinksRepository: FavoritesDrinksRepository) :
    FavoriteUseCase {

    override suspend fun allDrinks(): Flow<List<DrinkFavorite>> =
        favoritesDrinksRepository.allDrinks()

    override suspend fun drinkDetails(drinkId: Long): Flow<DrinkDetails> =
        favoritesDrinksRepository.drinkDetails(drinkId = drinkId)

    override suspend fun insertDrink(drinkFavorite: DrinkDetails): Flow<Long> {
        return favoritesDrinksRepository.insertDrink(drinkFavorite = drinkFavorite)
    }


    override suspend fun deleteDrink(drinkId: Long): Flow<Int> {
        return favoritesDrinksRepository.deleteDrink(drinkId = drinkId)
    }


    override suspend fun isFavorite(drinkId: Long): Flow<Boolean> =
        favoritesDrinksRepository.isFavorite(drinkId = drinkId)

}