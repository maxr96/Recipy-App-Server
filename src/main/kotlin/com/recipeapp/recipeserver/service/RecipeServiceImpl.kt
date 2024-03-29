package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.Recipe
import com.recipeapp.recipeserver.repository.RecipeRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.util.Optional

@Component
class RecipeServiceImpl(
    val recipeRepository: RecipeRepository,
    val unitService: UnitService,
    val ingredientService: IngredientService,
    val tagsService: TagService,
    val userService: UserService,
) : RecipeService {

    @Transactional(readOnly = true)
    override fun getAllRecipes(): Set<Recipe> {
        return recipeRepository.findAll().toSet()
    }

    @Transactional(readOnly = true)
    override fun getRecipeById(id: Int): Optional<Recipe> {
        return recipeRepository.findById(id)
    }

    override fun addRecipe(recipe: Recipe): Recipe {
        val existingUser = userService.getFirstByName(recipe.author.username)
        if (existingUser != null) {
            recipe.author = existingUser
        } else {
            // TODO: throw an application level exception and handle it on the controller.
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Author Not Found")
        }
        val existingUnits = unitService.getAllByNames(recipe.recipeIngredients.map { it.unit.name }).toMutableList()
        val existingIngredients = ingredientService.getAllByNames(recipe.recipeIngredients.map { it.ingredient.name }).toMutableList()
        val existingTags = tagsService.getAllByNames(recipe.tags.map { it.name }).toMutableList()

        for ((index, value) in recipe.recipeIngredients.withIndex()) {
            val foundUnit = existingUnits.firstOrNull { it?.name == value.unit.name }
            if (foundUnit != null) {
                recipe.recipeIngredients.elementAt(index).unit = foundUnit
            }
            existingUnits.add(value.unit)
            val foundIngredient = existingIngredients.firstOrNull { it?.name == value.ingredient.name }
            if (foundIngredient != null) {
                recipe.recipeIngredients.elementAt(index).ingredient = foundIngredient
            }
            existingIngredients.add(value.ingredient)
        }

        for ((index, v) in recipe.tags.withIndex()) {
            val foundTag = existingTags.firstOrNull { it?.name == v.name }
            if (foundTag != null) {
                recipe.tags.elementAt(index).id = foundTag.id
            }
            existingTags.add(v)
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
