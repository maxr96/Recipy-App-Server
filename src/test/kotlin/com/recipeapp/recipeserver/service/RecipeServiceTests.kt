package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import java.time.Instant.now
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest
@ActiveProfiles("test")
class RecipeServiceTests(@Autowired val recipeService: RecipeService) {

    @Test
    @Transactional
    fun `add, retrieve and delete recipes`() {
        val p = Recipe(1, "desc", "title", "wesd", setOf(
                RecipeIngredient(1, Ingredient(1, "meat"), MeasurementUnit(1, "pieces"), 20)),
        Date.from(now()), User(1, "me", "me@email.com"))
        assertThat(recipeService.getAllRecipes()).hasSize(0)
        recipeService.addRecipe(p)
        var recipes = recipeService.getAllRecipes()
        assertThat(recipes).hasSize(1)
        var storedRecipe = recipes.first()
        assertThat(p.title).isEqualTo(storedRecipe.title)
        assertThat(p.description).isEqualTo(storedRecipe.description)
        var storedRecipeOne = recipeService.getRecipeById(storedRecipe.id);
        assertThat(storedRecipe).isEqualToComparingFieldByField(storedRecipeOne.get())
         assertThat(p.recipeIngredients.first().amount).isEqualTo(storedRecipe.recipeIngredients.first().amount)
        recipeService.deleteRecipe(storedRecipe.id)
        assertThat(recipeService.getAllRecipes().isEmpty())
    }

    @Test
    @Transactional
    fun `add new recipe with duplicated unit and ingredient names`() {
        val p = Recipe(1, "desc", "title", "wesd", setOf(
                RecipeIngredient(1, Ingredient(1, "meat"), MeasurementUnit(1, "pieces"), 20)),
                Date.from(now()), User(1, "me", "me@email.com"))
        assertThat(recipeService.getAllRecipes()).hasSize(0)
        recipeService.addRecipe(p)
        var recipes = recipeService.getAllRecipes()
        assertThat(recipes).hasSize(1)

        val p2 = Recipe(1, "desc", "title", "wesd", setOf(
                RecipeIngredient(1, Ingredient(1, "meat"), MeasurementUnit(1, "pieces"), 20)),
                Date.from(now()), User(1, "me", "me@email.com"))
        recipeService.addRecipe(p2)
        recipes = recipeService.getAllRecipes()
        assertThat(recipes).hasSize(2)
    }
}