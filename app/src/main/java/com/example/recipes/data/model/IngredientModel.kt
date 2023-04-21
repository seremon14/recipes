package com.example.recipes.data.model

import com.google.gson.annotations.SerializedName

data class IngredientModel(
    @SerializedName("name") var name: String,
    @SerializedName("quantity") var quantity : String? = null
)
