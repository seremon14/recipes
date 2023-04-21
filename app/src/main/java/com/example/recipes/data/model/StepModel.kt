package com.example.recipes.data.model

import com.google.gson.annotations.SerializedName

data class StepModel(
    @SerializedName("order") var order: Int? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("advice") var advice: String? = null
)
