package com.example.recipes.data

import com.example.recipes.data.network.RecipeService
import com.example.recipes.domain.model.Recipe
import com.example.recipes.domain.model.toDomain
import javax.inject.Inject

class RecipeRepository @Inject constructor(
    private val api: RecipeService,
){
    suspend fun getAllRecipesFromApi(): List<Recipe>{
        val response = api.getRecipes()
        return response.map { it.toDomain() }
    }
}