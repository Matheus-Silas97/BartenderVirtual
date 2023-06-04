package com.example.letsdrink.data.remote.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse(

    @SerializedName("")
    val id: Long?,

    @SerializedName("")
    val name: String?,

    @SerializedName("")
    val description: String?

)
