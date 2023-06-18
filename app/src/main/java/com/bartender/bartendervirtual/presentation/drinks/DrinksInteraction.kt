package com.bartender.bartendervirtual.presentation.drinks

sealed class DrinksInteraction {

    object GoBackScreen : DrinksInteraction()
    data class Categories(val categoryId: Long) : DrinksInteraction()
    object CloseErrorDialog : DrinksInteraction()
    data class SelectDrink(val drinkId: Long) : DrinksInteraction()

}