package com.example.letsdrink.presentation.favorite

import com.example.letsdrink.domain.model.DrinkFavorite

data class FavoritesDrinksState(
    val favorites: List<DrinkFavorite> = listOf(),
    val success: Boolean = true,
    val isLoading: Boolean = false,
    val error: String? = null
)