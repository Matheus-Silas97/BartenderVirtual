package com.bartender.bartendervirtual.data.mapper

import com.bartender.bartendervirtual.common.utils.extensions.orZero
import com.bartender.bartendervirtual.data.remote.response.HomeInformationResponse
import com.bartender.bartendervirtual.domain.model.Category
import com.bartender.bartendervirtual.domain.model.DrinkHome
import com.bartender.bartendervirtual.domain.model.HomeInformation

fun HomeInformationResponse?.convertToHomeInformation(): HomeInformation = HomeInformation(
    drinksRecommendations = this?.drinkRecommendation?.map { drink ->
        DrinkHome(
            id = drink.id.orZero(),
            name = drink.name.orEmpty(),
            image = drink.image.orEmpty(),
            categoryId = drink.categoryId.orZero()
        )
    } ?: listOf(),
    categories = this?.categories?.map { category ->
        Category(
            id = category.id.orZero(),
            name = category.name.orEmpty(),
            description = category.description.orEmpty()
        )
    } ?: listOf(),
    title = this?.title.orEmpty()
)