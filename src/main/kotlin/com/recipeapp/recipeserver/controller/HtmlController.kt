package com.recipeapp.recipeserver.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/recipes")
class RecipeController {
    @GetMapping
    fun getRecipes(): String {
        return "recipes";
    }

    @GetMapping("/{recipeId}")
    fun getRecipe(@PathVariable recipeId: Int): String {
        return "recipe$recipeId";
    }
}