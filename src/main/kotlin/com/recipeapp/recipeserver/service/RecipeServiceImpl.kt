package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.Recipe
import com.recipeapp.recipeserver.repository.*
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class RecipeServiceImpl (var recipeRepository: RecipeRepository, var unitService: UnitService,
var ingredientService: IngredientService) : RecipeService {

    @Transactional(readOnly = true)
    override fun getAllRecipes(): Set<Recipe> {
        return recipeRepository.findAll().toSet()
    }

    @Transactional(readOnly = true)
    override fun getRecipeById(id: Int): Optional<Recipe> {
        return recipeRepository.findById(id)
    }

    override fun addRecipe(recipe: Recipe): Recipe {
        val existingUnits = unitService.getAllByNames(recipe.recipeIngredients.map{it.unit.name})
        val existingIngredients = ingredientService.getAllByNames(recipe.recipeIngredients.map{it.ingredient.name})
        if(existingUnits.isNotEmpty() || existingIngredients.isNotEmpty()){
            for((index, value) in recipe.recipeIngredients.withIndex()) {
                val foundUnit = existingUnits.firstOrNull { it?.name == value.unit.name }
                if (foundUnit != null) {
                    recipe.recipeIngredients.elementAt(index).unit = foundUnit
                }
                val foundIngredient = existingIngredients.firstOrNull{it?.name == value.ingredient.name}
                if (foundIngredient != null) {
                    recipe.recipeIngredients.elementAt(index).ingredient = foundIngredient
                }
            }
        }
        return recipeRepository.save(recipe)

    }

    override fun changeRecipe(recipe: Recipe): Recipe? {
        val storedRecipe = recipeRepository.findById(recipe.id)
        return if (storedRecipe.isPresent) {
            recipeRepository.save(recipe)
        } else {
            null
        }
    }

    override fun deleteRecipe(id: Int) {
        recipeRepository.deleteById(id)
    }
}