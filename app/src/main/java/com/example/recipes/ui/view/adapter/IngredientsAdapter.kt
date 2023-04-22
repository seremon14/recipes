package com.example.recipes.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.domain.model.Ingredient

class IngredientsAdapter(private val ingredients: ArrayList<Ingredient>): RecyclerView.Adapter<IngredientsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return IngredientsViewHolder(layoutInflater.inflate(R.layout.item_ingredients_list, parent, false))
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val item = ingredients[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = ingredients.size
}
