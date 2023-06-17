package com.example.letsdrink.data.remote.response

import com.google.gson.annotations.SerializedName

data class MeasureDrinkDetailsResponse(

    @SerializedName("id")
    val id: Long? = 0L,

    @SerializedName("name")
    val name: String? = ""

)
