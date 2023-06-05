package com.example.letsdrink.data.mapper

import com.example.letsdrink.common.utils.orZero
import com.example.letsdrink.data.local.entity.FavoriteEntity
import com.example.letsdrink.domain.model.DrinkFavorite

fun List<FavoriteEntity>.convertFavorite(): List<DrinkFavorite> = this.map {
    DrinkFavorite(
        id = it.id,
        name = it.name.orEmpty(),
        image = it.image.orEmpty(),
        garnish = it.garnish.orEmpty()
    )
}

fun DrinkFavorite.convertToDrinkFavoriteEntity(): FavoriteEntity = FavoriteEntity(
    id = this.id.orZero(),
    name = this.name,
    image = this.image,
    description = this.description,
    garnish = this.garnish
)