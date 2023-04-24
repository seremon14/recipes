package com.example.recipes.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.databinding.ActivityDetailMainBinding
import com.example.recipes.databinding.ActivityDetailBinding
import com.example.recipes.domain.model.Recipe
import com.example.recipes.ui.view.adapter.IngredientsAdapter
import com.example.recipes.ui.view.adapter.StepsAdapter
import com.example.recipes.ui.view.util.loadImage

class DetailActivity : AppCompatActivity() {

    private lateinit var bindingMain: ActivityDetailMainBinding

    companion object {
        lateinit var recipe: Recipe

        fun getStartIntent(context: Context, recipe: Recipe): Intent {
            DetailActivity.recipe = recipe
            return Intent(context, DetailActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityDetailMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        setSupportActionBar(bindingMain.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        bindingMain.toolbarLayout.title = recipe.name

        loadInfo()

        bindingMain.detail.buttonMap.setOnClickListener {
            startActivity(MapActivity.getStartIntent(this, recipe))
        }
    }

    private fun loadInfo() {
        //supportActionBar?.title = recipe.name
        bindingMain.imageRecipe.loadImage(recipe.image)
        bindingMain.detail.textRecipeName.text = recipe.name
        bindingMain.detail.textRecipeDescription.text = recipe.description

        recipe.steps?.let {
            val layoutManager = LinearLayoutManager(this)
            bindingMain.detail.recyclerSteps.layoutManager = layoutManager
            bindingMain.detail.recyclerSteps.setHasFixedSize(true)
            bindingMain.detail.recyclerSteps.isNestedScrollingEnabled = false
            val adapterSteps = StepsAdapter(it)
            bindingMain.detail.recyclerSteps.adapter = adapterSteps
        }

        recipe.ingredients?.let {
            val layoutManager = LinearLayoutManager(this)
            bindingMain.detail.recyclerIngredients.layoutManager = layoutManager
            bindingMain.detail.recyclerIngredients.setHasFixedSize(true)
            bindingMain.detail.recyclerIngredients.isNestedScrollingEnabled = false
            val adapterIngredients = IngredientsAdapter(it)
            bindingMain.detail.recyclerIngredients.adapter = adapterIngredients
        }
    }
}