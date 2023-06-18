package com.bartender.bartendervirtual.presentation.ingredients_details

import com.bartender.bartendervirtual.domain.model.Drinks

data class IngredientsDetailsState(
    val id: Long = 0L,
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val drinks: List<Drinks> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)
