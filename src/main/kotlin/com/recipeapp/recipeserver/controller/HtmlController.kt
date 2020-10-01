package com.recipeapp.recipeserver.controller

import com.recipeapp.recipeserver.model.MeasurmentUnit
import com.recipeapp.recipeserver.model.Recipe
import com.recipeapp.recipeserver.service.RecipeService
import com.recipeapp.recipeserver.service.UnitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/recipes")
class RecipeController(
        private val recipeService: RecipeService,
        private val unitService: UnitService
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

    @PostMapping("/unit")
    fun postUnit(@RequestBody unit: MeasurmentUnit): ResponseEntity<MeasurmentUnit> {
        val unit = unitService.addUnit(unit);
        return if(unit != null) {
            ResponseEntity.ok().body(unit)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}