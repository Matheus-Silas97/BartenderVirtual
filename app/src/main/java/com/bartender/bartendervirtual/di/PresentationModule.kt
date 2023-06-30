package com.bartender.bartendervirtual.di

import com.bartender.bartendervirtual.presentation.drink_details.DrinkDetailsViewModel
import com.bartender.bartendervirtual.presentation.drinks.DrinksViewModel
import com.bartender.bartendervirtual.presentation.favorite.FavoriteViewModel
import com.bartender.bartendervirtual.presentation.home.HomeViewModel
import com.bartender.bartendervirtual.presentation.ingredients_details.IngredientsDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { HomeViewModel(homeInformationUseCase = get()) }

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