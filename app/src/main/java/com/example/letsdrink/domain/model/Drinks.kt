package com.example.letsdrink.domain.model

data class Drinks(
    val id: Long? = 0,
    val name: String = "",
    val image: String = "",
    val ingredients: List<Ingredients> = listOf()
)
