package com.bartender.bartendervirtual.presentation.favorite

import com.bartender.bartendervirtual.domain.model.DrinkFavorite

data class FavoritesDrinksState(
    val favorites: List<DrinkFavorite> = listOf(),
    val success: Boolean = true,
    val isLoading: Boolean = false,
    val error: String? = null
)