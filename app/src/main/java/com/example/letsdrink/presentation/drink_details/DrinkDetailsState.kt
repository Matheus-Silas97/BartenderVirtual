package com.example.letsdrink.presentation.drink_details

import com.example.letsdrink.domain.model.Ingredients

data class DrinkDetailsState(
    val id: Long = 0L,
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val ingredients: List<Ingredients> = listOf(),
    val prepareMode: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)