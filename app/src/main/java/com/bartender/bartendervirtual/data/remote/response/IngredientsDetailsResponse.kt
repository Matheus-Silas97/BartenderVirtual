package com.bartender.bartendervirtual.data.remote.response

import com.google.gson.annotations.SerializedName

data class IngredientsDetailsResponse(
    @SerializedName("id")
    val id: Long = 0L,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("image")
    val image: String = "",

    @SerializedName("description")
    val description: String = "",

    @SerializedName("drinks")
    val drinks: List<DrinkResponse> = listOf()

)
