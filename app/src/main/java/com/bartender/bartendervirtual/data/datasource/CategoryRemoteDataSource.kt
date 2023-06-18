package com.bartender.bartendervirtual.data.datasource

import com.bartender.bartendervirtual.domain.model.Category

interface CategoryRemoteDataSource {

    suspend fun categories(): List<Category>

}