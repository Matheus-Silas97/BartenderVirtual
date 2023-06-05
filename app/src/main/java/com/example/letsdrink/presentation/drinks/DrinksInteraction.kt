package com.example.letsdrink.presentation.drinks

sealed class DrinksInteraction {

    object GoBackScreen : DrinksInteraction()
    data class SelectDrink(val drinkId: Long) : DrinksInteraction()

}