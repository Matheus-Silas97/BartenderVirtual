package com.example.letsdrink.data.remote.response

import com.google.gson.annotations.SerializedName

data class DrinkResponse(

    @SerializedName("id")
    val id: Long? = 0,

    @SerializedName("name")
    val name: String? = "",

    @SerializedName("image")
    val image: String? = "",

    @SerializedName("description")
    val description: String? = "",

    @SerializedName("garnish")
    val garnish: String? = "",

    @SerializedName("category_id")
    val categoryId: Int? = 0

)
