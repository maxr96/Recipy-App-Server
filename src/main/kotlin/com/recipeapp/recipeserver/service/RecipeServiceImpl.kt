package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.Recipe
import com.recipeapp.recipeserver.repository.RecipeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RecipeServiceImpl : RecipeService {
    @Autowired
    private lateinit var recipeRepository: RecipeRepository


    override fun getAllRecipes(): Set<Recipe> {
        return recipeRepository.findAll().toSet()
    }

    override fun getRecipeById(id: Long): Recipe? {
        return recipeRepository.getById(id);
    }

    override fun addRecipe(recipe: Recipe): Recipe {
        return recipeRepository.save(recipe)
    }

    override fun changeRecipe(recipe: Recipe): Recipe {
        TODO("Not yet implemented")
    }

}