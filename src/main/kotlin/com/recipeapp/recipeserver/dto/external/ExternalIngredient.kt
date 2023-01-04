package com.recipeapp.recipeserver.dto.external

data class ExternalIngredient(
    val id: Int,
    val image: String,
    val localizedName: String,
    val name: String,
)
