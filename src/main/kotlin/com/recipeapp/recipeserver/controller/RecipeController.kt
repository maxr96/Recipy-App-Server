package com.recipeapp.recipeserver.controller

import com.recipeapp.recipeserver.dto.RecipeDTO
import com.recipeapp.recipeserver.dto.mapToDto
import com.recipeapp.recipeserver.dto.mapToEntity
import com.recipeapp.recipeserver.service.RecipeService
import org.springframework.http.HttpStatus
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
    fun getRecipes(): ResponseEntity<List<RecipeDTO>> {
        val recipes = recipeService.getAllRecipes()
        return if(recipes.isNotEmpty()){
            ResponseEntity.ok().body(recipes.map { it.mapToDto() })
        } else {
            ResponseEntity.notFound().build()
        }

    }

    @GetMapping("/{recipeId}")
    fun getRecipe(@PathVariable recipeId: Int): ResponseEntity<RecipeDTO> {
        val recipe = recipeService.getRecipeById(recipeId)
        return if(recipe.isPresent) {
            ResponseEntity.ok().body(recipe.get().mapToDto())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun postRecipe(@RequestBody recipe: RecipeDTO): ResponseEntity<RecipeDTO> {
        val addedRecipe = recipeService.addRecipe(recipe.mapToEntity())
        val location: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(addedRecipe.id).toUri()
        return ResponseEntity.created(location).build()
    }

    @PutMapping
    fun updateRecipe(@RequestBody recipe: RecipeDTO): ResponseEntity<RecipeDTO> {
        val changedRecipe = recipeService.changeRecipe(recipe.mapToEntity()) ?: return ResponseEntity.notFound().build()
        val location: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(changedRecipe.id).toUri()
        return ResponseEntity.created(location).build()
    }

    @DeleteMapping
    fun deleteRecipe(@PathVariable id: Int): ResponseEntity<RecipeDTO> {
        recipeService.deleteRecipe(id)
        return ResponseEntity.noContent().build()
    }
}