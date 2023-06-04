package com.example.letsdrink.data.remote.response

import com.example.letsdrink.domain.model.Drinks
import com.google.gson.annotations.SerializedName

data class IngredientsResponse(

    @SerializedName("")
    val id: Long = 0,

    @SerializedName("")
    val name: String = "",

    @SerializedName("")
    val image: String = "",

    @SerializedName("")
    val description: String = "",

    @SerializedName("")
    val relatedDrinks: List<Drinks> = listOf()

)
