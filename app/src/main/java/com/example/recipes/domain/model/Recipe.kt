package com.example.recipes.domain.model

import com.example.recipes.data.model.RecipeModel

data class Recipe(
    val image: String,
    val name: String,
    val description: String,
    val ingredients: ArrayList<Ingredient>?,
    val steps: ArrayList<Step>?,
    val latitude: String?,
    val longitude: String?
)

fun RecipeModel.toDomain() = Recipe(
    image,
    name,
    description,
    ingredients?.let { arrayList -> ArrayList(arrayList.map { it.toDomain() }) },
    steps?.let { arrayList -> ArrayList(arrayList.map { it.toDomain() }) },
    latitude,
    longitude
)
