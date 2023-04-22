package com.example.recipes.ui.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemStepsListBinding
import com.example.recipes.domain.model.Step
import com.example.recipes.ui.view.util.loadImage

class StepsViewHolder(inflate: View) : RecyclerView.ViewHolder(inflate) {

    private val binding = ItemStepsListBinding.bind(inflate)

    fun render(step: Step) {
        binding.tvDescription.text = step.description

        if (!step.advice.isNullOrEmpty()) {
            binding.tvAdvice.visibility = View.VISIBLE
            binding.tvAdvice.text = step.advice
        } else {
            binding.tvAdvice.visibility = View.GONE
        }

        if (!step.image.isNullOrEmpty()) {
            binding.ivImage.visibility = View.VISIBLE
            step.image?.let { binding.ivImage.loadImage(it) }
        } else {
            binding.ivImage.visibility = View.GONE
        }
    }
}
