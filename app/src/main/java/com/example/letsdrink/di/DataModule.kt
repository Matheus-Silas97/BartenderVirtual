package com.example.letsdrink.di

import androidx.room.Room
import com.example.letsdrink.data.datasource.CategoryRemoteDataSource
import com.example.letsdrink.data.datasource.CategoryRemoteDataSourceImpl
import com.example.letsdrink.data.datasource.DrinksRemoteDataSource
import com.example.letsdrink.data.datasource.DrinksRemoteDataSourceImpl
import com.example.letsdrink.data.datasource.FavoritesDrinksLocalDataSource
import com.example.letsdrink.data.datasource.FavoritesDrinksLocalDataSourceImpl
import com.example.letsdrink.data.datasource.IngredientsRemoteDataSource
import com.example.letsdrink.data.datasource.IngredientsRemoteDataSourceImpl
import com.example.letsdrink.data.local.AppDatabase
import com.example.letsdrink.data.remote.client.Apifactory
import com.example.letsdrink.data.remote.service.DrinkService
import com.example.letsdrink.data.repository.CategoryRepositoryImpl
import com.example.letsdrink.data.repository.DrinksRepositoryImpl
import com.example.letsdrink.data.repository.FavoritesDrinksRepositoryImpl
import com.example.letsdrink.data.repository.IngredientsRepositoryImpl
import com.example.letsdrink.domain.repository.CategoryRepository
import com.example.letsdrink.domain.repository.DrinksRepository
import com.example.letsdrink.domain.repository.FavoritesDrinksRepository
import com.example.letsdrink.domain.repository.IngredientsRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


val dataModule = module {

    factory { Room.databaseBuilder(get(), AppDatabase::class.java, "lets_drink_database").build() }

    single { Apifactory.create(serviceClass = DrinkService::class.java) }

    single { Dispatchers.IO }

    factory<DrinksRemoteDataSource> { DrinksRemoteDataSourceImpl(service = get(), defaultDispatcher = get()) }

    factory<CategoryRemoteDataSource> { CategoryRemoteDataSourceImpl(service = get(), defaultDispatcher = get()) }

    factory<IngredientsRemoteDataSource> { IngredientsRemoteDataSourceImpl(service = get()) }

    factory<FavoritesDrinksLocalDataSource> { FavoritesDrinksLocalDataSourceImpl(appDatabase = get()) }




    factory<DrinksRepository> { DrinksRepositoryImpl(drinksRemoteDataSource = get()) }

    factory<CategoryRepository> { CategoryRepositoryImpl(categoryRemoteDataSource = get()) }

    factory<IngredientsRepository> { IngredientsRepositoryImpl(ingredientsRemoteDataSource = get()) }

    factory<FavoritesDrinksRepository> { FavoritesDrinksRepositoryImpl(favoritesDrinksLocalDataSource = get()) }


}