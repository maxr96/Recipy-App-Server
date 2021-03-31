package com.recipeapp.recipeserver.dto.external

data class ExternalStep(
    val ingredients: List<ExternalIngredient>,
    val number: Int,
    val step: String
)
