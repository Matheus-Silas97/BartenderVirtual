package com.bartender.bartendervirtual.data.remote.response

import com.google.gson.annotations.SerializedName

data class HomeInformationResponse(

    @SerializedName("drinks")
    val drinkRecommendation: List<RecommendationsResponse>?,

    @SerializedName("categories")
    val categories: List<CategoryResponse>?,

    @SerializedName("title")
    val title: String?

)
