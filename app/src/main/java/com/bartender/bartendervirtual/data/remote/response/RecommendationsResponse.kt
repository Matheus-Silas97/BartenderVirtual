package com.bartender.bartendervirtual.data.remote.response

import com.google.gson.annotations.SerializedName

data class RecommendationsResponse(

    @SerializedName("")
    val id: Long,

    @SerializedName("")
    val name: String,

    @SerializedName("")
    val image: String

)
