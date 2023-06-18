package com.bartender.bartendervirtual.di

import androidx.room.Room
import com.bartender.bartendervirtual.data.datasource.CategoryRemoteDataSource
import com.bartender.bartendervirtual.data.datasource.CategoryRemoteDataSourceImpl
import com.bartender.bartendervirtual.data.datasource.DrinksRemoteDataSource
import com.bartender.bartendervirtual.data.datasource.DrinksRemoteDataSourceImpl
import com.bartender.bartendervirtual.data.datasource.FavoritesDrinksLocalDataSource
import com.bartender.bartendervirtual.data.datasource.FavoritesDrinksLocalDataSourceImpl
import com.bartender.bartendervirtual.data.datasource.IngredientsRemoteDataSource
import com.bartender.bartendervirtual.data.datasource.IngredientsRemoteDataSourceImpl
import com.bartender.bartendervirtual.data.local.AppDatabase
import com.bartender.bartendervirtual.data.remote.client.Apifactory
import com.bartender.bartendervirtual.data.remote.service.DrinkService
import com.bartender.bartendervirtual.data.repository.CategoryRepositoryImpl
import com.bartender.bartendervirtual.data.repository.DrinksRepositoryImpl
import com.bartender.bartendervirtual.data.repository.FavoritesDrinksRepositoryImpl
import com.bartender.bartendervirtual.data.repository.IngredientsRepositoryImpl
import com.bartender.bartendervirtual.domain.repository.CategoryRepository
import com.bartender.bartendervirtual.domain.repository.DrinksRepository
import com.bartender.bartendervirtual.domain.repository.FavoritesDrinksRepository
import com.bartender.bartendervirtual.domain.repository.IngredientsRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module


val dataModule = module {

    factory { Room.databaseBuilder(get(), AppDatabase::class.java, "lets_drink_db").build() }

    single { Apifactory.create(serviceClass = DrinkService::class.java) }

    single { Dispatchers.IO }



    factory<DrinksRemoteDataSource> {
        DrinksRemoteDataSourceImpl(
            service = get(),
            defaultDispatcher = get()
        )
    }

    factory<CategoryRemoteDataSource> {
        CategoryRemoteDataSourceImpl(
            service = get(),
            defaultDispatcher = get()
        )
    }

    factory<IngredientsRemoteDataSource> { IngredientsRemoteDataSourceImpl(service = get()) }

    factory<FavoritesDrinksLocalDataSource> { FavoritesDrinksLocalDataSourceImpl(appDatabase = get()) }



    factory<DrinksRepository> { DrinksRepositoryImpl(drinksRemoteDataSource = get()) }

    factory<CategoryRepository> { CategoryRepositoryImpl(categoryRemoteDataSource = get()) }

    factory<IngredientsRepository> { IngredientsRepositoryImpl(ingredientsRemoteDataSource = get()) }

    factory<FavoritesDrinksRepository> {
        FavoritesDrinksRepositoryImpl(
            favoritesDrinksLocalDataSource = get()
        )
    }


}