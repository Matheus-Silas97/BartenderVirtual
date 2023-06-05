package com.example.letsdrink.presentation.home

sealed class HomeInteraction {

    object CloseErrorDialog : HomeInteraction()

    object getCateories : HomeInteraction()

}