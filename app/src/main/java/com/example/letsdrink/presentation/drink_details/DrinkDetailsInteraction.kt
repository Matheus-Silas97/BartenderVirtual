package com.example.letsdrink.presentation.drink_details

import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.DrinkFavorite

sealed class DrinkDetailsInteraction {

    object NavigationClickBackPressed : DrinkDetailsInteraction()

    object CloseErrorDialog : DrinkDetailsInteraction()

    data class FavoriteDrink(val drinkId: Long) : DrinkDetailsInteraction()

    data class CardClickInteraction(val drinkId: Long) : DrinkDetailsInteraction()

}