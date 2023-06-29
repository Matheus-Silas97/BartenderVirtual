package com.bartender.bartendervirtual.presentation.home

import com.bartender.bartendervirtual.domain.model.Category
import com.bartender.bartendervirtual.domain.model.DrinkHome

data class HomeState(
    val categories: List<Category> = listOf(),
    val recommendations: List<DrinkHome> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = "abc"
)
