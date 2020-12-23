package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.Recipe
import java.util.*

interface RecipeService {
    fun getAllRecipes(): Set<Recipe>
    fun getRecipeById(id: Int): Optional<Recipe>
    fun addRecipe(recipe: Recipe): Recipe
    fun changeRecipe(recipe: Recipe): Recipe?
    fun deleteRecipe(id: Int)
}
