package com.example.recipes.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.domain.model.Recipe

class RecipesAdapter(private var recipes: List<Recipe>, private val onClickListener:(Recipe) -> Unit)
    : RecyclerView.Adapter<RecipesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecipesViewHolder(layoutInflater.inflate(R.layout.item_recipe_list, parent, false))
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val item = recipes[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = recipes.size

    fun filterList(filterList: List<Recipe>) {
        recipes = filterList
        notifyDataSetChanged()
    }
}