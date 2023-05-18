package com.example.letsdrink.domain.model

data class DrinkDetailsModel(
    val id: Long = 0,
    val name: String = "",
    val image: String = "",
    val ingredients: List<IngredientsModel> = listOf(),
    val modePrepare: List<String> = listOf()
)
