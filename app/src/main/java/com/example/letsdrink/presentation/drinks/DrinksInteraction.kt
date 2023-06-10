package com.example.letsdrink.presentation.drinks

sealed class DrinksInteraction {

    object GoBackScreen : DrinksInteraction()

    object CloseErrorDialog: DrinksInteraction()
    data class SelectDrink(val drinkId: Long) : DrinksInteraction()

}