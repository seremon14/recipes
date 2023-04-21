package com.example.recipes.data.model

import com.google.gson.annotations.SerializedName

data class RecipeModel(
    @SerializedName("image") var image: String,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("ingredients") var ingredients: ArrayList<IngredientModel>? = null,
    @SerializedName("steps") var steps: ArrayList<StepModel>? = null,
    @SerializedName("location") var location: String?= null
)
