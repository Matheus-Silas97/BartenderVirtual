package com.example.letsdrink.domain.model

data class DrinkDetails(
    val id: Long = 0,
    val name: String = "",
    val image: String = "",
    val description: String = "",
    val garnish: String = "",
    val modePrepare: String = "",
    val ingredients: List<IngredientDrinkDetails> = listOf()
)
