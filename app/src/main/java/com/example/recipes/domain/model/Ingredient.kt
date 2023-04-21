package com.example.recipes.domain.model

import com.example.recipes.data.model.IngredientModel

data class Ingredient(val name: String, val quantity: String?)

fun IngredientModel.toDomain() = Ingredient(name, quantity)

