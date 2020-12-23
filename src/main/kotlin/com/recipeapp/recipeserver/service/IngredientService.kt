package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.Ingredient

interface IngredientService {
    fun getAllByNames(names: List<String>): List<Ingredient?>
}
