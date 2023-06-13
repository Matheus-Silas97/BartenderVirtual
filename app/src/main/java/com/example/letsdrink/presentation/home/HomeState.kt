package com.example.letsdrink.presentation.home

import com.example.letsdrink.domain.model.Category

data class HomeState(
    val categories: List<Category> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = "abc"
)
