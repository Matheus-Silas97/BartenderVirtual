package com.example.letsdrink.data.remote.response

import com.google.gson.annotations.SerializedName

data class IngredientsDrinkDetailsResponse(

    @SerializedName("id")
    val id: Long? = 0L,

    @SerializedName("name")
    val name: String? = "",

    @SerializedName("description")
    val description: String? = "",

    @SerializedName("image")
    val image: String? = ""

)
