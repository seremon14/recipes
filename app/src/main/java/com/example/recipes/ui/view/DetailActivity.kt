package com.example.recipes.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    }

    private fun loadInfo() {
        binding.ivImage.loadImage(recipe.image)
        binding.ivName.text = recipe.name
        binding.ivDescription.text = recipe.description

        recipe.ingredients?.let {
            val layoutManager = LinearLayoutManager(this)
            binding.rvIngredients.layoutManager = layoutManager
            binding.rvIngredients.setHasFixedSize(false)
            binding.rvIngredients.isNestedScrollingEnabled = false

            val adapterIngredients = IngredientsAdapter(it)
            binding.rvIngredients.adapter = adapterIngredients
        }

        recipe.steps?.let {
            val layoutManager = LinearLayoutManager(this)
            binding.rvSteps.layoutManager = layoutManager
            binding.rvSteps.setHasFixedSize(false)
            binding.rvSteps.isNestedScrollingEnabled = false

            val adapterSteps = StepsAdapter(it)
            binding.rvSteps.adapter = adapterSteps
        }
    }
}