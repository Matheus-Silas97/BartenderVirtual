package com.example.letsdrink.data.mapper

import com.example.letsdrink.data.response.DrinkDetailsResponse
import com.example.letsdrink.data.response.DrinkResponse
import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.Drinks
import com.example.letsdrink.domain.model.Ingredients

fun List<DrinkResponse>?.toDrinkListEntity(): List<Drinks> = this?.map {
    Drinks(
        id = it.id ?: 0L,
        name = it.name ?: "",
        image = it.image ?: ""
    )
} ?: listOf()

fun DrinkDetailsResponse?.toDrinkDetailsEntity(): DrinkDetails = DrinkDetails(
    id = this?.id ?: 0L,
    name = this?.name ?: "",
    image = this?.image ?: "",
    ingredients = this?.ingredients?.map { ingredientsItem ->
        Ingredients(
            id = ingredientsItem.id,
            name = ingredientsItem.name,
            image = ingredientsItem.image
        )
    } ?: listOf(),
    modePrepare = this?.modePrepare ?: listOf()
)


