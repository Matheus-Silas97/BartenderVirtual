package com.example.letsdrink.presentation.favorite

import com.example.letsdrink.presentation.drinks.DrinksInteraction

sealed class FavoriteDrinksInteraction {

    data class SelectDrink(val drinkId: Long) : FavoriteDrinksInteraction()

    data class RemoveFavorite(val drinkId: Long) : FavoriteDrinksInteraction()

}