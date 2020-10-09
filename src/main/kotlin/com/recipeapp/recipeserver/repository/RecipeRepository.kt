package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.Recipe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RecipeRepository : JpaRepository<Recipe, Int> {
    fun getById(id: Int): Recipe?
// TODO: Properly fetch all recipes
//    @Query("SELECT a from Recipe LEFT JOIN FETCH a.recipe_ingredient")
//    fun getAll(): Set<Recipe>
}