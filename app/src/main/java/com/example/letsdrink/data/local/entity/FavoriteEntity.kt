package com.example.letsdrink.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_drinks")
data class FavoriteEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String?,

    val image: String?,

    val description: String?,

    val garnish: String?

)
