package com.example.letsdrink.presentation.drink_details

import com.example.letsdrink.domain.model.IngredientDrinkDetails
import com.example.letsdrink.domain.model.IngredientsListDrinkDetails

data class DrinkDetailsState(
    val id: Long = 0L,
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val garnish: String = "",
    val ingredients: List<IngredientsListDrinkDetails> = listOf(),
    val prepareMode: String = "",
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)