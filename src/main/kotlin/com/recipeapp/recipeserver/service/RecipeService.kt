package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.Recipe

interface RecipeService {
    fun getAllRecipes(): Set<Recipe>
    fun getRecipeById(id: Long): Recipe?
    fun addRecipe(recipe: Recipe): Recipe
    fun changeRecipe(recipe: Recipe): Recipe
}