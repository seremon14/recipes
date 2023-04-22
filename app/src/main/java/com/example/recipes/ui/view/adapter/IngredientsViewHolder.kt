package com.example.recipes.ui.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemIngredientsListBinding
import com.example.recipes.domain.model.Ingredient

class IngredientsViewHolder (inflate: View) : RecyclerView.ViewHolder(inflate) {

    private val binding = ItemIngredientsListBinding.bind(inflate)

    fun render(ingredient: Ingredient) {
        binding.tvIngredient.text = ingredient.name
        binding.tvQuantity.text = ingredient.quantity
    }
}