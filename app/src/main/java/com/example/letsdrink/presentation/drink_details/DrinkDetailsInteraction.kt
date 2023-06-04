package com.example.letsdrink.presentation.drink_details

import com.example.letsdrink.domain.model.DrinkDetails

sealed class DrinkDetailsInteraction {

    object NavigationClickBackPressed : DrinkDetailsInteraction()

    data class SaveDrinkInFavorite(val drink: DrinkDetails): DrinkDetailsInteraction()

    data class GetDrinkDetails(val drinkId: Long): DrinkDetailsInteraction()

}