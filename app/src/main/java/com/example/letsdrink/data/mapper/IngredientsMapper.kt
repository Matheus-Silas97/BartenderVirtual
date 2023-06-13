package com.example.letsdrink.data.mapper

import com.example.letsdrink.common.utils.extensions.orZero
import com.example.letsdrink.data.remote.response.IngredientsDetailsResponse
import com.example.letsdrink.domain.model.Drinks
import com.example.letsdrink.domain.model.Ingredients

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