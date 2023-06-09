package com.example.recipes.ui.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipes.databinding.ItemRecipeListBinding
import com.example.recipes.domain.model.Recipe
import com.example.recipes.ui.view.util.loadImage

class RecipesViewHolder(inflate: View) : RecyclerView.ViewHolder(inflate) {

    private val binding = ItemRecipeListBinding.bind(inflate)

    fun render(recipe: Recipe, onClickListener: (Recipe) -> Unit) {
        binding.textTitle.text = recipe.name
        binding.textDescription.text = recipe.description
        binding.imageRecipe.loadImage(recipe.image)
        itemView.setOnClickListener { onClickListener(recipe) }
    }
}