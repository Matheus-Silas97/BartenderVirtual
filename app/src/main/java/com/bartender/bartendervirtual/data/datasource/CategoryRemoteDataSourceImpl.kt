package com.bartender.bartendervirtual.data.datasource

import com.bartender.bartendervirtual.data.mapper.convertCategoryEntity
import com.bartender.bartendervirtual.data.remote.service.DrinkService
import com.bartender.bartendervirtual.domain.model.Category
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRemoteDataSourceImpl(
    private val service: DrinkService,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : CategoryRemoteDataSource {

    override suspend fun categories(): List<Category> =
        withContext(defaultDispatcher) {
            service.categories().convertCategoryEntity()
        }


}