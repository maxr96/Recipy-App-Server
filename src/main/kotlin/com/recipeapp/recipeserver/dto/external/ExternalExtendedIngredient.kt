package com.recipeapp.recipeserver.dto.external

data class ExternalExtendedIngredient(
    val amount: Double,
    val image: String,
    val measures: ExternalMeasures,
    val name: String,
    val nameClean: String,
    val original: String,
    val originalName: String,
    val originalString: String,
    val unit: String
)
