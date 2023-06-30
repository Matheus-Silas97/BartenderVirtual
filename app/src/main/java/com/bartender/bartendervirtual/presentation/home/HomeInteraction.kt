package com.bartender.bartendervirtual.presentation.home

sealed class HomeInteraction {

    data class NavigateNextScreen(val categoryId: Long, val categoryName: String) :
        HomeInteraction()

    data class GoToDetailsDrink(val drinkId: Long) : HomeInteraction()

    object CloseErrorDialog : HomeInteraction()

}