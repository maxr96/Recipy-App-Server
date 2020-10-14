package com.recipeapp.recipeserver.dto

import com.recipeapp.recipeserver.model.Recipe
import java.util.*

data class RecipeDTO (val id: Int, val title: String, val description: String, val instructions: String,
                      val recipeIngredients: List<RecipeIngredientDTO>, val time: Date, val author: String)

fun Recipe.mapToDto(): RecipeDTO {
    return RecipeDTO(
            this.id,
            this.title,
            this.description,
            this.instructions,
            this.recipeIngredients.map { it.mapToDto() },
            this.time,
            this.author.username
    )
}