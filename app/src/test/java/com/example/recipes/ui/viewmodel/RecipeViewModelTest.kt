package com.example.recipes.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.recipes.domain.GetRecipes
import com.example.recipes.domain.model.Recipe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
internal class RecipeViewModelTest {

    @RelaxedMockK
    private lateinit var getRecipesUseCase: GetRecipes

    private lateinit var recipeViewModel: RecipeViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        recipeViewModel = RecipeViewModel(getRecipesUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created at the first time, get all recipes and set the first value`() =
        runTest {
            //Given
            val recipeList = listOf(Recipe(
                "Image_Url 1",
                "Name 1",
                "Description 1",
                null,
                null,
                null,
                null
            ), Recipe(
                "Image_Url 2",
                "Name 2",
                "Description 2",
                null,
                null,
                null,
                null
            ))
            coEvery { getRecipesUseCase() } returns recipeList

            //When
            recipeViewModel.onCreate()

            //Then
            assert(recipeViewModel.recipeModel.value?.first() == recipeList.first())
        }
}