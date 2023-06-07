package com.example.letsdrink.di

import com.example.letsdrink.domain.usecase.CategoryUseCaseImpl
import com.example.letsdrink.domain.usecase.CategoryUseCase
import com.example.letsdrink.domain.usecase.DrinksUseCase
import com.example.letsdrink.domain.usecase.DrinksUseCaseImpl
import com.example.letsdrink.domain.usecase.FavoriteUseCase
import com.example.letsdrink.domain.usecase.FavoriteUseCaseImpl
import com.example.letsdrink.domain.usecase.IngredientsUseCase
import com.example.letsdrink.domain.usecase.IngredientsUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    factory<DrinksUseCase> { DrinksUseCaseImpl(drinksRepository = get()) }

    factory<CategoryUseCase> { CategoryUseCaseImpl(categoryRepository = get()) }

    factory<FavoriteUseCase> { FavoriteUseCaseImpl(favoritesDrinksRepository = get()) }

    factory<IngredientsUseCase> { IngredientsUseCaseImpl(ingredientsRepository = get()) }

}