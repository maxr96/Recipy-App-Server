package com.recipeapp.recipeserver.controller

import com.recipeapp.recipeserver.dto.RecipeDTO
import com.recipeapp.recipeserver.dto.external.ExternalRecipeDTO
import com.recipeapp.recipeserver.dto.external.mapToInternalEntity
import com.recipeapp.recipeserver.dto.mapToDto
import com.recipeapp.recipeserver.dto.mapToEntity
import com.recipeapp.recipeserver.service.RecipeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI
import java.security.Principal

@RestController
@RequestMapping("/recipes")
class RecipeController(
    private val recipeService: RecipeService,
) {
    @GetMapping
    fun getRecipes(): ResponseEntity<List<RecipeDTO>> {
        val recipes = recipeService.getAllRecipes()
        return if (recipes.isNotEmpty()) {
            ResponseEntity.ok().body(recipes.map { it.mapToDto() })
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/{recipeId}")
    fun getRecipe(@PathVariable recipeId: Int): ResponseEntity<RecipeDTO> {
        val recipe = recipeService.getRecipeById(recipeId)
        return if (recipe.isPresent) {
            ResponseEntity.ok().body(recipe.get().mapToDto())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun postRecipe(@RequestBody recipe: RecipeDTO, principal: Principal): ResponseEntity<RecipeDTO> {
        val addedRecipe = recipeService.addRecipe(recipe.mapToEntity(principal.name))
        val location: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(addedRecipe.id).toUri()
        return ResponseEntity.created(location).build()
    }

    @PutMapping
    fun updateRecipe(@RequestBody recipe: RecipeDTO, principal: Principal): ResponseEntity<RecipeDTO> {
        val changedRecipe = recipeService.changeRecipe(recipe.mapToEntity(principal.name)) ?: return ResponseEntity.notFound().build()
        val location: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(changedRecipe.id).toUri()
        return ResponseEntity.created(location).build()
    }

    @DeleteMapping("/{recipeId}")
    fun deleteRecipe(@PathVariable recipeId: Int): ResponseEntity<RecipeDTO> {
        recipeService.deleteRecipe(recipeId)
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/external")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun createRecipeFromExternalAPI(@RequestBody recipe: ExternalRecipeDTO, principal: Principal): ResponseEntity<RecipeDTO> {
        val internalRecipes = recipe.mapToInternalEntity(principal.name)
        for (internalRecipe in internalRecipes) {
            recipeService.addRecipe(internalRecipe)
        }
        return ResponseEntity.ok().build()
    }
}
