package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.Recipe
import com.recipeapp.recipeserver.repository.*
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

    @Autowired
    private lateinit var authorRepository: AuthorRepository


    override fun getAllRecipes(): Set<Recipe> {
        return recipeRepository.findAll().toSet()
    }

    override fun getRecipeById(id: Int): Recipe? {
        return recipeRepository.getById(id)
    }

    override fun addRecipe(recipe: Recipe): Recipe {
        authorRepository.save(recipe.author);
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