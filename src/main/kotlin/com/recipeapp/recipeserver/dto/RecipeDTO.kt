package com.recipeapp.recipeserver.dto

import com.recipeapp.recipeserver.model.Category
import com.recipeapp.recipeserver.model.Recipe
import com.recipeapp.recipeserver.model.Tag
import com.recipeapp.recipeserver.model.User
import java.time.Duration

data class RecipeDTO(
    val id: Int = 0,
    val title: String,
    val description: String,
    val cuisine: String,
    val category: Category,
    val instructions: String,
    val ingredients: List<RecipeIngredientDTO>,
    val time: Duration,
    val author: String = "",
    val imagePath: String = "",
    val creditsText: String = "",
    val tags: MutableSet<String>,
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
        this.creditsText,
        this.tags.map { it.name }.toMutableSet(),
    )
}

fun RecipeDTO.mapToEntity(username: String): Recipe {
    val recipe = Recipe(
        this.id,
        this.title,
        this.description,
        this.category,
        this.cuisine,
        this.instructions,
        time = this.time,
        author = User(username = username),
        imagePath = this.imagePath,
        creditsText = this.creditsText,
        tags = this.tags.map { Tag(name = it) }.toMutableSet(),
    )
    ingredients.forEach { recipe.addRecipeIngredient(it.mapToEntity(recipe)) }
    return recipe
}
