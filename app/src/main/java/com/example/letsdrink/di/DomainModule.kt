package com.example.letsdrink.di

import com.example.letsdrink.domain.usecase.DrinksUseCase
import com.example.letsdrink.domain.usecase.DrinksUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    factory<DrinksUseCase> { DrinksUseCaseImpl(repository = get()) }

}