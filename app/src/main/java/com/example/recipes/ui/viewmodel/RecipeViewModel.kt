package com.example.recipes.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.GetRecipes
import com.example.recipes.domain.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val getRecipes: GetRecipes
): ViewModel(){
    val recipeModel = MutableLiveData<List<Recipe>>()
    val isLoading = MutableLiveData<Boolean>()
    val navigateToDetail = MutableLiveData<Recipe>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getRecipes()

            result?.let {
                recipeModel.postValue(it)
                isLoading.postValue(false)
            }
        }
    }
}