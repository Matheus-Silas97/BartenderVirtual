package com.example.letsdrink.domain.model

data class Ingredients(
    val id: Long = 0,
    val name: String = "",
    val image: String = "",
    val description: String = "",
    val relatedDrinks: List<Drinks> = listOf()
)
