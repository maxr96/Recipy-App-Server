package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.RecipeIngredient
import org.springframework.data.jpa.repository.JpaRepository

interface RecipeIngredientRepository : JpaRepository<RecipeIngredient, Long> {
}