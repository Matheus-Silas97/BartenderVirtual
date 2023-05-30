package com.example.letsdrink.data.response

import com.example.letsdrink.domain.model.Ingredients
import com.google.gson.annotations.SerializedName

data class DrinkDetailsResponse(

    @SerializedName("")
    val id: Long? = 0,

    @SerializedName("")
    val name: String? = "",

    @SerializedName("")
    val image: String? = "",

    @SerializedName("")
    val ingredients: List<Ingredients>? = listOf(),

    @SerializedName("")
    val modePrepare: List<String>? = listOf()

)
