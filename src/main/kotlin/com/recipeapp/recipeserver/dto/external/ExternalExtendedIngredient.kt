package com.recipeapp.recipeserver.dto.external

import com.recipeapp.recipeserver.model.Ingredient
import com.recipeapp.recipeserver.model.MeasurementUnit
import com.recipeapp.recipeserver.model.Recipe
import com.recipeapp.recipeserver.model.RecipeIngredient

data class ExternalExtendedIngredient(
    val amount: Double,
    val image: String,
    val measures: ExternalMeasures,
    val name: String,
    val nameClean: String,
    val original: String,
    val originalName: String,
    val unit: String,
)

fun ExternalExtendedIngredient.mapToInternalEntity(recipe: Recipe): RecipeIngredient {
    return RecipeIngredient(
        amount = amount,
        ingredient = Ingredient(name = name),
        unit = MeasurementUnit(name = unit),
        recipe = recipe,
    )
}
