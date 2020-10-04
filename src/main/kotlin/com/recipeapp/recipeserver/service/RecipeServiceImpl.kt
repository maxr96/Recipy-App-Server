package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.MeasurementUnit
import com.recipeapp.recipeserver.model.Recipe
import com.recipeapp.recipeserver.repository.IngredientRepository
import com.recipeapp.recipeserver.repository.RecipeIngredientRepository
import com.recipeapp.recipeserver.repository.RecipeRepository
import com.recipeapp.recipeserver.repository.UnitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RecipeServiceImpl : RecipeService {
    @Autowired
    private lateinit var recipeRepository: RecipeRepository

    @Autowired
    private lateinit var ingredientRepository: IngredientRepository

    @Autowired
    private lateinit var recipeIngredientRepository: RecipeIngredientRepository

    @Autowired
    private lateinit var unitRepository: UnitRepository


    override fun getAllRecipes(): Set<Recipe> {
        return recipeRepository.findAll().toSet()
    }

    override fun getRecipeById(id: Long): Recipe? {
        return recipeRepository.getById(id)
    }

    override fun addRecipe(recipe: Recipe): Recipe {
        for(recipeIngredient in recipe.recipeIngredients){
            ingredientRepository.save(recipeIngredient.ingredient)
            unitRepository.save(recipeIngredient.unit)
            recipeIngredientRepository.save(recipeIngredient)
        }

        return recipeRepository.save(recipe)
    }

    override fun changeRecipe(recipe: Recipe): Recipe {
        TODO("Not yet implemented")
    }
}