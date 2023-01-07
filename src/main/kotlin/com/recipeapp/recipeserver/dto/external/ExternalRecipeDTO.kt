package com.recipeapp.recipeserver.dto.external

import com.recipeapp.recipeserver.model.Recipe

data class ExternalRecipeDTO(
    val recipes: List<ExternalRecipe>,
)

fun ExternalRecipeDTO.mapToInternalEntity(): List<Recipe> = recipes.map { it.mapToInternalEntity() }
