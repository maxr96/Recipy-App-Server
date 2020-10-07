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
    fun getRecipe(@PathVariable recipeId: Int): ResponseEntity<Recipe> {
        val recipe = recipeService.getRecipeById(recipeId)
        return if(recipe.isPresent) {
            ResponseEntity.ok().body(recipe.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun postRecipe(@RequestBody recipe: Recipe): ResponseEntity<Recipe> {
        val addedRecipe = recipeService.addRecipe(recipe)
        val location: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(addedRecipe.id).toUri()
        return ResponseEntity.created(location).build()
    }

    @PutMapping
    fun updateRecipe(@RequestBody recipe: Recipe): ResponseEntity<Recipe> {
        val changedRecipe = recipeService.changeRecipe(recipe) ?: return ResponseEntity.notFound().build()
        val location: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(changedRecipe.id).toUri()
        return ResponseEntity.created(location).build()
    }
}