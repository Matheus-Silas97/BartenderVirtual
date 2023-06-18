package com.bartender.bartendervirtual.data.mapper

import com.bartender.bartendervirtual.common.utils.extensions.orZero
import com.bartender.bartendervirtual.data.local.entity.FavoriteEntity
import com.bartender.bartendervirtual.data.remote.response.DrinkDetailsResponse
import com.bartender.bartendervirtual.data.remote.response.DrinkResponse
import com.bartender.bartendervirtual.domain.model.DrinkDetails
import com.bartender.bartendervirtual.domain.model.Drinks
import com.bartender.bartendervirtual.domain.model.IngredientDrinkDetails
import com.bartender.bartendervirtual.domain.model.IngredientsListDrinkDetails
import com.bartender.bartendervirtual.domain.model.MeasureDrinkDetails

fun List<DrinkResponse>?.convertDrinkListEntity(): List<Drinks> = this?.map {
    Drinks(
        id = it.id.orZero(),
        name = it.name.orEmpty(),
        description = it.description.orEmpty(),
        image = it.image.orEmpty(),
        garnish = it.garnish.orEmpty(),
        categoryId = it.categoryId.orZero()
    )
} ?: listOf()

fun DrinkDetailsResponse?.convertDrinkDetailsEntity(): DrinkDetails = DrinkDetails(
    id = this?.id.orZero(),
    name = this?.name.orEmpty(),
    garnish = this?.garnish.orEmpty(),
    image = this?.image.orEmpty(),
    description = this?.description.orEmpty(),
    prepareMode = this?.prepareMode.orEmpty(),
    ingredients = this?.ingredients?.map { ingredient ->
        IngredientsListDrinkDetails(
            ingredient = IngredientDrinkDetails(
                id = ingredient.ingredient?.id.orZero(),
                name = ingredient.ingredient?.name.orEmpty(),
                description = ingredient.ingredient?.description.orEmpty(),
                image = ingredient.ingredient?.image.orEmpty()
            ),
            measure = MeasureDrinkDetails(
                id = ingredient.measure?.id.orZero(),
                name = ingredient.measure?.name.orEmpty()
            ),
            amount = ingredient.amount.orZero()
        )
    } ?: listOf()
)

fun FavoriteEntity.convertDrinkDetails(): DrinkDetails = DrinkDetails(
    id = this.id.orZero(),
    name = this.name.orEmpty(),
    garnish = this.garnish.orEmpty(),
    image = this.image.orEmpty(),
    description = this.description.orEmpty(),
    prepareMode = this.prepareMode.orEmpty(),
    ingredients = listOf()
)


