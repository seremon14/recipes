package com.example.recipes.domain

import com.example.recipes.data.RecipeRepository
import com.example.recipes.domain.model.Recipe
import javax.inject.Inject

class GetRecipes @Inject constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(): List<Recipe>? {
        return repository.getAllRecipesFromApi()
    }
}