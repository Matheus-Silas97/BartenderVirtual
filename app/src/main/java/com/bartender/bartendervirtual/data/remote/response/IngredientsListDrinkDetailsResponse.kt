package com.bartender.bartendervirtual.data.remote.response

import com.google.gson.annotations.SerializedName

data class IngredientsListDrinkDetailsResponse(

    @SerializedName("ingredient")
    val ingredient: IngredientsDrinkDetailsResponse? = IngredientsDrinkDetailsResponse(),

    @SerializedName("measure")
    val measure: MeasureDrinkDetailsResponse? = MeasureDrinkDetailsResponse(),

    @SerializedName("amount")
    val amount: Float? = 0F
)