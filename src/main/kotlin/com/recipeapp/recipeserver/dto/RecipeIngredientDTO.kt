package com.recipeapp.recipeserver.dto

import com.recipeapp.recipeserver.model.Ingredient
import com.recipeapp.recipeserver.model.MeasurementUnit
import com.recipeapp.recipeserver.model.RecipeIngredient

data class RecipeIngredientDTO (val id: Int, val ingredient: String, val unit: String, val amount: Short)

fun RecipeIngredient.mapToDto(): RecipeIngredientDTO {
    return RecipeIngredientDTO(
            this.id,
            this.ingredient.name,
            this.unit.name,
            this.amount
    )
}

fun RecipeIngredientDTO.mapToEntity(): RecipeIngredient {
    return RecipeIngredient(
            this.id,
            Ingredient(name = ingredient),
            MeasurementUnit(name = unit),
            amount
    )
}