package com.example.letsdrink.data.datasource

import com.example.letsdrink.domain.model.Category

interface CategoryRemoteDataSource {

    suspend fun categories(): List<Category>

}