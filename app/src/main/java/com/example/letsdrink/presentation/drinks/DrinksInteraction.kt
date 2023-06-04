package com.example.letsdrink.presentation.drinks

sealed class DrinksInteraction {

    data class SelectDrink(val drinkId: Long): DrinksInteraction()

}