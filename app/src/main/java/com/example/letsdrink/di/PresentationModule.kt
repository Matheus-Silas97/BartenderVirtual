package com.example.letsdrink.di

import com.example.letsdrink.presentation.drink_details.DrinkDetailsViewModel
import com.example.letsdrink.presentation.drinks.DrinksViewModel
import com.example.letsdrink.presentation.favorite.FavoriteViewModel
import com.example.letsdrink.presentation.ingredients_details.IngredientsDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { DrinkDetailsViewModel() }

    viewModel { DrinksViewModel() }

    viewModel { FavoriteViewModel() }

    viewModel { IngredientsDetailsViewModel() }

}