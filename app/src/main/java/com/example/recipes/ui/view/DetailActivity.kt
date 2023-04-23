package com.example.recipes.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.databinding.ActivityDetailBinding
import com.example.recipes.domain.model.Recipe
import com.example.recipes.ui.view.adapter.IngredientsAdapter
import com.example.recipes.ui.view.adapter.StepsAdapter
import com.example.recipes.ui.view.util.loadImage

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        lateinit var recipe: Recipe

        fun getStartIntent(context: Context, recipe: Recipe): Intent {
            DetailActivity.recipe = recipe
            return Intent(context, DetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadInfo()

        binding.buttonMap.setOnClickListener {
            startActivity(MapActivity.getStartIntent(this, recipe))
        }
    }

    private fun loadInfo() {
        supportActionBar?.title = recipe.name
        binding.imageRecipe.loadImage(recipe.image)
        binding.textRecipeName.text = recipe.name
        binding.textRecipeDescription.text = recipe.description

        recipe.ingredients?.let {
            val layoutManager = LinearLayoutManager(this)
            binding.recyclerIngredients.layoutManager = layoutManager
            val adapterIngredients = IngredientsAdapter(it)
            binding.recyclerIngredients.adapter = adapterIngredients
//            binding.recyclerIngredients.setHasFixedSize(true)
//            binding.recyclerIngredients.isNestedScrollingEnabled = false
//            ViewCompat.setNestedScrollingEnabled(binding.recyclerIngredients, false)

            binding.recyclerIngredients.layoutParams.height = 1000
        }

        recipe.steps?.let {
            val layoutManager = LinearLayoutManager(this)
            binding.recyclerSteps.layoutManager = layoutManager
            binding.recyclerSteps.setHasFixedSize(false)
            binding.recyclerSteps.isNestedScrollingEnabled = false

            val adapterSteps = StepsAdapter(it)
            binding.recyclerSteps.adapter = adapterSteps
            binding.recyclerSteps.setHasFixedSize(true)
            binding.recyclerSteps.isNestedScrollingEnabled = false
            ViewCompat.setNestedScrollingEnabled(binding.recyclerSteps, false)
        }
    }
}