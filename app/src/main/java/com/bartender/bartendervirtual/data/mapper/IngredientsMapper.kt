package com.bartender.bartendervirtual.data.mapper

import com.bartender.bartendervirtual.common.utils.extensions.orZero
import com.bartender.bartendervirtual.data.remote.response.IngredientsDetailsResponse
import com.bartender.bartendervirtual.domain.model.Drinks
import com.bartender.bartendervirtual.domain.model.Ingredients

fun IngredientsDetailsResponse?.convertIngredientsDetails(): Ingredients = Ingredients(
    id = this?.id.orZero(),
    name = this?.name.orEmpty(),
    image = this?.image.orEmpty(),
    description = this?.description.orEmpty(),
    relatedDrinks = this?.drinks?.map { drinksItem ->
        Drinks(
            id = drinksItem.id.orZero(),
            name = drinksItem.name.orEmpty(),
            image = drinksItem.image.orEmpty(),
            description = drinksItem.description.orEmpty()
        )
    } ?: listOf()
)