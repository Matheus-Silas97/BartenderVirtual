package com.bartender.bartendervirtual.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecommendationsResponse(

    @SerializedName("id")
    val id: Long?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("image")
    val image: String?,

    @SerializedName("category_id")
    val categoryId: Long?

)
