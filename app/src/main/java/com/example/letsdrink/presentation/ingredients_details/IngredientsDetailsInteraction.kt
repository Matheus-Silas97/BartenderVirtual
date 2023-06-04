package com.example.letsdrink.presentation.ingredients_details

sealed class IngredientsDetailsInteraction {

    object NavigationClickBackPressed : IngredientsDetailsInteraction()

    data class SelectDrink(val drinkId: Long) : IngredientsDetailsInteraction()

    object FavoriteDrink : IngredientsDetailsInteraction()

}