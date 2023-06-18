package com.bartender.bartendervirtual.domain.model

data class Drinks(
    val id: Long? = 0,
    val name: String = "",
    val description: String = "",
    val image: String = "",
    val garnish: String = "",
    val categoryId: Int = 0
)
