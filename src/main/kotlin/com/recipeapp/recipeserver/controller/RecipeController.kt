package com.recipeapp.recipeserver.controller

import com.recipeapp.recipeserver.model.Recipe
import com.recipeapp.recipeserver.service.RecipeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI


@RestController
@RequestMapping("/recipes")
class RecipeController(
        private val recipeService: RecipeService
)
{
    @GetMapping
    fun getRecipes(): ResponseEntity<Set<Recipe>> {
        val recipes = recipeService.getAllRecipes()
        return if(recipes.isNotEmpty()){
            ResponseEntity.ok().body(recipes)
        } else {
            ResponseEntity.notFound().build()
        }

    }

    @GetMapping("/{recipeId}")
    fun getRecipe(@PathVariable recipeId: Long): ResponseEntity<Recipe> {
        val recipe = recipeService.getRecipeById(recipeId)
        return if(recipe != null) {
            ResponseEntity.ok().body(recipe)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun postRecipe(@RequestBody recipe: Recipe): ResponseEntity<Recipe> {
        val recipe = recipeService.addRecipe(recipe)
        val location: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(recipe.id).toUri()
        return ResponseEntity.created(location).build()
    }
}