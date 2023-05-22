package com.example.letsdrink.domain.model

data class IngredientsModel(
    val id: Long = 0,
    val name: String = "",
    val image: String = "",
    val description: String = "",
    val relatedDrinks: List<DrinksModel> = listOf()
)
