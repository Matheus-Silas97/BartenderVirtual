package com.example.letsdrink.data.mapper

import com.example.letsdrink.common.utils.extensions.orZero
import com.example.letsdrink.data.remote.response.CategoryResponse
import com.example.letsdrink.domain.model.Category

fun List<CategoryResponse>?.convertCategoryEntity(): List<Category> = this?.map {
    Category(
        id = it.id.orZero(),
        name = it.name.orEmpty(),
        description = it.description.orEmpty()
    )
} ?: listOf()