package com.bartender.bartendervirtual.data.remote.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse(

    @SerializedName("id")
    val id: Long?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("description")
    val description: String?

)
