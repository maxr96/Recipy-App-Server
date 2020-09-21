package com.recipeapp.recipeserver.controller

import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/recipes")
class RecipeController {
    @GetMapping
    fun getRecipes(model: Model): String {
        model["title"] = "Blog"
        return "recipes";
    }

    @GetMapping("/{recipeId}")
    fun getRecipe(@PathVariable recipeId: Int): String {
        return "recipe$recipeId";
    }
}