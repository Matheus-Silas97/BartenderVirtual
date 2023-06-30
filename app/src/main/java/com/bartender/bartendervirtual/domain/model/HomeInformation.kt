package com.bartender.bartendervirtual.domain.model

data class HomeInformation(

    val drinksRecommendations: List<DrinkHome>,

    val categories: List<Category>,

    val title: String

)
