package com.bartender.bartendervirtual.data.mapper

import com.bartender.bartendervirtual.common.utils.extensions.orZero
import com.bartender.bartendervirtual.data.local.entity.FavoriteEntity
import com.bartender.bartendervirtual.domain.model.DrinkDetails
import com.bartender.bartendervirtual.domain.model.DrinkFavorite

fun List<FavoriteEntity>.convertFavorite(): List<DrinkFavorite> = this.map {
    DrinkFavorite(
        id = it.id.orZero(),
        name = it.name.orEmpty(),
        image = it.image.orEmpty(),
        garnish = it.garnish.orEmpty()
    )
}

fun DrinkDetails.convertToDrinkFavoriteEntity(): FavoriteEntity = FavoriteEntity(
    id = this.id.orZero(),
    name = this.name,
    image = this.image,
    description = this.description,
    garnish = this.garnish,
    prepareMode = this.prepareMode
)