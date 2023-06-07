package com.example.letsdrink.presentation.home

sealed class HomeInteraction {

    data class NavigateNextScreen(val categoryId: Long, val categoryName: String) :
        HomeInteraction()

    object CloseErrorDialog : HomeInteraction()

}