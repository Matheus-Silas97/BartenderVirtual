package com.example.letsdrink.di

import com.example.letsdrink.data.repository.DrinksRepositoryImpl
import com.example.letsdrink.domain.repository.DrinksRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


val dataModule = module {
//    single { Apifactory.create(serviceClass = DrinksRepository::class.java) }

    single { Dispatchers.IO }


    factory<DrinksRepository> { DrinksRepositoryImpl(service = get(), defaultDispatcher = get()) }
}