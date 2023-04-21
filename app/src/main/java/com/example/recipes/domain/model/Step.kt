package com.example.recipes.domain.model

import com.example.recipes.data.model.StepModel

data class Step(
    val order: Int?,
    val description: String?,
    val image: String?,
    val advice: String?
)

fun StepModel.toDomain() = Step(order, description, image, advice)

