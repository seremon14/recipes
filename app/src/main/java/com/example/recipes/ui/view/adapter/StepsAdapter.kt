package com.example.recipes.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.R
import com.example.recipes.domain.model.Step

class StepsAdapter(private val steps: ArrayList<Step>): RecyclerView.Adapter<StepsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return StepsViewHolder(layoutInflater.inflate(R.layout.item_steps_list, parent, false))
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        val item = steps[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = steps.size
}
