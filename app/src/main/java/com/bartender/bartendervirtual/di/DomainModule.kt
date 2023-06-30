package com.bartender.bartendervirtual.di

import com.bartender.bartendervirtual.domain.usecase.CategoryUseCase
import com.bartender.bartendervirtual.domain.usecase.CategoryUseCaseImpl
import com.bartender.bartendervirtual.domain.usecase.DrinksUseCase
import com.bartender.bartendervirtual.domain.usecase.DrinksUseCaseImpl
import com.bartender.bartendervirtual.domain.usecase.FavoriteUseCase
import com.bartender.bartendervirtual.domain.usecase.FavoriteUseCaseImpl
import com.bartender.bartendervirtual.domain.usecase.HomeInformationUseCase
import com.bartender.bartendervirtual.domain.usecase.HomeInformationUseCaseImpl
import com.bartender.bartendervirtual.domain.usecase.IngredientsUseCase
import com.bartender.bartendervirtual.domain.usecase.IngredientsUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    factory<DrinksUseCase> { DrinksUseCaseImpl(drinksRepository = get()) }

    factory<CategoryUseCase> { CategoryUseCaseImpl(categoryRepository = get()) }

    factory<FavoriteUseCase> { FavoriteUseCaseImpl(favoritesDrinksRepository = get()) }

    factory<IngredientsUseCase> { IngredientsUseCaseImpl(ingredientsRepository = get()) }

    factory<HomeInformationUseCase> { HomeInformationUseCaseImpl(homeInformationRepository = get()) }

}