package com.example.letsdrink.domain.model

data class DrinkDetails(
    val id: Long = 0,
    val name: String = "",
    val image: String = "",
    val description: String = "",
    val garnish: String = "",
    val ingredients: List<Ingredients> = listOf(),
    val modePrepare: List<String> = listOf()
)
