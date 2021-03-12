package com.recipeapp.recipeserver.dto

import com.recipeapp.recipeserver.model.Category
import com.recipeapp.recipeserver.model.Recipe
import com.recipeapp.recipeserver.model.User
import java.util.*

data class RecipeDTO(
    val id: Int = 0,
    val title: String,
    val description: String,
    val cuisine: String,
    val category: Category,
    val instructions: String,
    val recipeIngredients: List<RecipeIngredientDTO>,
    val time: Date,
    val author: String,
    val imagePath: String = "",
    val creditsText: String
)

fun Recipe.mapToDto(): RecipeDTO {
    return RecipeDTO(
        this.id,
        this.title,
        this.description,
        this.cuisine,
        this.category,
        this.instructions,
        this.recipeIngredients.map { it.mapToDto() },
        this.time,
        this.author.username,
        this.imagePath,
        this.creditsText
    )
}

fun RecipeDTO.mapToEntity(): Recipe {
    val recipe = Recipe(
        this.id,
        this.title,
        this.description,
        this.category,
        this.cuisine,
        this.instructions,
        time = this.time,
        author = User(username = this.author),
        imagePath = this.imagePath,
        creditsText = this.creditsText
    )
    recipeIngredients.forEach { recipe.addRecipeIngredient(it.mapToEntity(recipe)) }
    return recipe
}
