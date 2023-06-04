package com.example.letsdrink.data.datasource

import com.example.letsdrink.data.mapper.convertCategoryEntity
import com.example.letsdrink.data.remote.service.DrinkService
import com.example.letsdrink.domain.model.Category
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRemoteDataSourceImpl(
    private val service: DrinkService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : CategoryRemoteDataSource {

    override suspend fun categories(): List<Category> =
        withContext(defaultDispatcher) {
            service.getCategories().convertCategoryEntity()
        }


}