package com.recipeapp.recipeserver.dto.external

import com.recipeapp.recipeserver.model.Category
import com.recipeapp.recipeserver.model.Recipe
import com.recipeapp.recipeserver.model.Tag
import com.recipeapp.recipeserver.model.User
import java.time.Duration

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
    val title: String,
)

fun ExternalRecipe.mapToInternalEntity(username: String): Recipe {
    val recipe = Recipe(
        title = title,
        description = summary,
        category = Category.APPETIZER, // TODO: Introduce proper category converter using dishTypes array.
        creditsText = creditsText,
        cuisine = cuisines.firstOrNull() ?: "", // TODO: Shall we consider storing more cuisines?
        author = User(username = username),
        imagePath = image,
        instructions = extendedIngredients.map { it.originalName }.toString(),
        time = Duration.ofMinutes(readyInMinutes.toLong()),
        tags = dishTypes.map { dishType -> Tag(name = dishType) }.toMutableSet(), // TODO: May be not a proper tag
    )
    extendedIngredients.forEach { recipe.addRecipeIngredient(it.mapToInternalEntity(recipe)) }
    return recipe
}
