package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.Ingredient
import org.springframework.data.jpa.repository.JpaRepository

interface IngredientRepository : JpaRepository<Ingredient, Int> {
    fun findAllByNameIn(name: List<String>): List<Ingredient?>
}
