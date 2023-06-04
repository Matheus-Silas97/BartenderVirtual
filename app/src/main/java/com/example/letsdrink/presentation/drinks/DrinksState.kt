package com.example.letsdrink.presentation.drinks

import com.example.letsdrink.domain.model.Drinks

data class DrinksState(
    val drinks: List<Drinks> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)