package com.example.letsdrink.data.remote.response

import com.google.gson.annotations.SerializedName

data class DrinkResponse(

    @SerializedName("")
    val id: Long? = 0,

    @SerializedName("")
    val name: String? = "",

    @SerializedName("")
    val image: String? = "",

    @SerializedName("")
    val ingredients: List<IngredientsResponse>? = listOf()

)
