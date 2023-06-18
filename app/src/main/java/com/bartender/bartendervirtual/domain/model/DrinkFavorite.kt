package com.bartender.bartendervirtual.domain.model

data class DrinkFavorite(
    val id: Long = 0,
    val name: String = "",
    val image: String = "",
    val description: String = "",
    val garnish: String = "",
    val prepareMode: String = ""
)