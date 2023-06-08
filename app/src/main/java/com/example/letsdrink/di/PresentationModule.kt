package com.example.letsdrink.di

import com.example.letsdrink.presentation.drink_details.DrinkDetailsViewModel
import com.example.letsdrink.presentation.drinks.DrinksViewModel
import com.example.letsdrink.presentation.favorite.FavoriteViewModel
import com.example.letsdrink.presentation.home.HomeViewModel
import com.example.letsdrink.presentation.ingredients_details.IngredientsDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { HomeViewModel(categoryUseCase = get()) }

    viewModel { DrinksViewModel(drinkUseCase = get()) }

    viewModel { params ->
        DrinkDetailsViewModel(
            drinkId = params[0],
            drinkUseCase = get(),
            favoriteUseCase = get()
        )
    }

    viewModel { params ->
        IngredientsDetailsViewModel(
            ingredientId = params[0],
            ingredientsUseCase = get()
        )
    }

    viewModel { FavoriteViewModel(favoriteUseCase = get()) }

}