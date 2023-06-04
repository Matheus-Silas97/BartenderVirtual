package com.example.letsdrink.domain.model

import com.example.letsdrink.common.enums.CategoriesDrinks

data class Drinks(
    val id: Long? = 0,
    val name: String = "",
    val image: String = "",
    val garnish: String = "",
    val category: CategoriesDrinks = CategoriesDrinks.NONE,
    val ingredients: List<Ingredients> = listOf()
)
