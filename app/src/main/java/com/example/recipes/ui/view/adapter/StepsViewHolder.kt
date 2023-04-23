package com.example.recipes.ui.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemStepsListBinding
import com.example.recipes.domain.model.Step
import com.example.recipes.ui.view.util.loadImage

class StepsViewHolder(inflate: View) : RecyclerView.ViewHolder(inflate) {

    private val binding = ItemStepsListBinding.bind(inflate)

    fun render(step: Step) {
        binding.textDescription.text = step.description

        if (!step.advice.isNullOrEmpty()) {
            binding.textAdvice.visibility = View.VISIBLE
            binding.textAdvice.text = step.advice
        } else {
            binding.textAdvice.visibility = View.GONE
        }

        if (!step.image.isNullOrEmpty()) {
            binding.imageStep.visibility = View.VISIBLE
            step.image?.let { binding.imageStep.loadImage(it) }
        } else {
            binding.imageStep.visibility = View.GONE
        }
    }
}
