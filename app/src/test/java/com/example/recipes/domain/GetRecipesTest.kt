package com.example.recipes.domain

import com.example.recipes.data.RecipeRepository
import com.example.recipes.domain.model.Recipe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetRecipesTest{

    @RelaxedMockK
    private lateinit var recipeRepository: RecipeRepository

    lateinit var getRecipes: GetRecipes

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRecipes = GetRecipes(recipeRepository)
    }

    @Test
    fun `When the api return something then get values from api`() = runBlocking{
        //Given
        val myList = listOf(
            Recipe(
                "Image_Url",
                "Name",
                "Description",
                null,
                null,
                null,
                null
            )
        )
        coEvery { recipeRepository.getAllRecipesFromApi() } returns myList

        //When
        val response = getRecipes()

        //Then
        assert(myList == response)
    }
}