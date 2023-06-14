package com.example.letsdrink.presentation.favorite

sealed class FavoriteDrinksInteraction {

    object CloseErrorDialog : FavoriteDrinksInteraction()

    object AllFavoritesDrinks : FavoriteDrinksInteraction()

    data class SelectDrink(val drinkId: Long) : FavoriteDrinksInteraction()

    data class RemoveFavorite(val drinkId: Long) : FavoriteDrinksInteraction()

}