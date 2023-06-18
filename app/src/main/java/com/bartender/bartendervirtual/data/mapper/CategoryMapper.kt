package com.bartender.bartendervirtual.data.mapper

import com.bartender.bartendervirtual.common.utils.extensions.orZero
import com.bartender.bartendervirtual.data.remote.response.CategoryResponse
import com.bartender.bartendervirtual.domain.model.Category

fun List<CategoryResponse>?.convertCategoryEntity(): List<Category> = this?.map {
    Category(
        id = it.id.orZero(),
        name = it.name.orEmpty(),
        description = it.description.orEmpty()
    )
} ?: listOf()