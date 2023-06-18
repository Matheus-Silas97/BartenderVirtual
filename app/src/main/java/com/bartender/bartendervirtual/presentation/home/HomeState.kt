package com.bartender.bartendervirtual.presentation.home

import com.bartender.bartendervirtual.domain.model.Category

data class HomeState(
    val categories: List<Category> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = "abc"
)
