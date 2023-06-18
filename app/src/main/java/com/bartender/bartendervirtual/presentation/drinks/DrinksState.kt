package com.bartender.bartendervirtual.presentation.drinks

import com.bartender.bartendervirtual.domain.model.Drinks

data class DrinksState(
    val drinks: List<Drinks> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)