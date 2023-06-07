package com.example.letsdrink.presentation.ingredients_details

sealed class IngredientsDetailsInteraction {

    object NavigationClickBackPressed : IngredientsDetailsInteraction()

    object CloseErrorDialog : IngredientsDetailsInteraction()

    data class SelectDrink(val drinkId: Long) : IngredientsDetailsInteraction()

}