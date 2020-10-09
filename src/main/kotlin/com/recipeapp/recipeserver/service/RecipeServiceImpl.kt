package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.Recipe
import com.recipeapp.recipeserver.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class RecipeServiceImpl : RecipeService {
    @Autowired
    private lateinit var recipeRepository: RecipeRepository

    @Autowired
    private lateinit var unitService: UnitService

    @Transactional(readOnly = true)
    override fun getAllRecipes(): Set<Recipe> {
        return recipeRepository.findAll().toSet()
    }

    @Transactional(readOnly = true)
    override fun getRecipeById(id: Int): Optional<Recipe> {
        return recipeRepository.findById(id)
    }

    override fun addRecipe(recipe: Recipe): Recipe {
        //TODO: check units and ingredients to not duplicate them.
//        recipe.recipeIngredients.map { if(unitService.getOneByName(it.unit.name) !== null{
//                    it.unit} return it.unit}
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