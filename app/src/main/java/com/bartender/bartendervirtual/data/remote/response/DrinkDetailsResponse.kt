package com.bartender.bartendervirtual.data.remote.response

import com.google.gson.annotations.SerializedName

data class DrinkDetailsResponse(

    @SerializedName("id")
    val id: Long? = 0,

    @SerializedName("name")
    val name: String? = "",

    @SerializedName("description")
    val description: String? = "",

    @SerializedName("garnish")
    val garnish: String? = "",

    @SerializedName("image")
    val image: String? = "",

    @SerializedName("ingredients")
    val ingredients: List<IngredientsListDrinkDetailsResponse>? = listOf(),

    @SerializedName("prepare_mode")
    val prepareMode: String? = "",

    @SerializedName("category_id")
    val categoryId: Int? = 0

)
