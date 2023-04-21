package com.example.recipes.ui.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipes.databinding.ItemRecipeListBinding
import com.example.recipes.domain.model.Recipe

class RecipesViewHolder(inflate: View) : RecyclerView.ViewHolder(inflate) {

    private val binding = ItemRecipeListBinding.bind(inflate)

    fun render(recipe: Recipe, onClickListener: (Recipe) -> Unit) {
        binding.tvTitle.text = recipe.name
        binding.tvDescription.text = recipe.description
        Glide.with(binding.ivImage.context).load(recipe.image).into(binding.ivImage)
        itemView.setOnClickListener { onClickListener(recipe) }
    }
}