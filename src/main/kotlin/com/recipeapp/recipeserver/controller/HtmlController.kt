package com.recipeapp.recipeserver.controller

import com.recipeapp.recipeserver.model.Recipe
import com.recipeapp.recipeserver.service.RecipeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/recipes")
class RecipeController(
        private val recipeService: RecipeService
)
{
    @GetMapping
    fun getRecipes(): ResponseEntity<Set<Recipe>> {
        val recipies = recipeService.getAllRecipes();
        return if(recipies.isNotEmpty()){
            ResponseEntity.ok().body(recipies);
        } else {
            ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{recipeId}")
    fun getRecipe(@PathVariable recipeId: Long): ResponseEntity<Recipe> {
        val recipe = recipeService.getRecipeById(recipeId);
        return if(recipe != null) {
            ResponseEntity.ok().body(recipe)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}