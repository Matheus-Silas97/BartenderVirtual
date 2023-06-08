package com.example.letsdrink.data.mapper

import com.example.letsdrink.common.utils.extensions.orZero
import com.example.letsdrink.data.local.entity.FavoriteEntity
import com.example.letsdrink.domain.model.DrinkDetails
import com.example.letsdrink.domain.model.DrinkFavorite

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