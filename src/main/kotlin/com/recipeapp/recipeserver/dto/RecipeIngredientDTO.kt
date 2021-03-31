package com.recipeapp.recipeserver.dto

import com.recipeapp.recipeserver.model.Ingredient
import com.recipeapp.recipeserver.model.MeasurementUnit
import com.recipeapp.recipeserver.model.Recipe
import com.recipeapp.recipeserver.model.RecipeIngredient

data class RecipeIngredientDTO(val name: String, val unit: String, val amount: Short)

fun RecipeIngredient.mapToDto(): RecipeIngredientDTO {
    return RecipeIngredientDTO(
        this.ingredient.name,
        this.unit.name,
        this.amount
    )
}

fun RecipeIngredientDTO.mapToEntity(recipe: Recipe): RecipeIngredient {
    return RecipeIngredient(
        ingredient = Ingredient(name = name),
        unit = MeasurementUnit(name = unit),
        amount = amount,
        recipe = recipe
    )
}
