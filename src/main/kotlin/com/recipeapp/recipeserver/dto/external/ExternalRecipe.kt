package com.recipeapp.recipeserver.dto.external

data class ExternalRecipe(
    val aggregateLikes: Int,
    val analyzedInstructions: List<ExternalInstruction>,
    val creditsText: String,
    val cuisines: List<String>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExternalExtendedIngredient>,
    val image: String,
    val imageType: String,
    val instructions: String,
    val occasions: List<String>,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceUrl: String,
    val summary: String,
    val title: String
)
