package com.example.letsdrink.presentation.favorite

import com.example.letsdrink.domain.model.Drinks

data class FavoritesDrinksState (
    val favorites: List<Drinks> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)