package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.Recipe
import org.springframework.data.jpa.repository.JpaRepository

interface RecipeRepository : JpaRepository<Recipe, Int> {
    fun getById(id: Int): Recipe?
}