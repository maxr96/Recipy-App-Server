package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.*
import com.recipeapp.recipeserver.service.RecipeService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.Instant.now
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest
@ActiveProfiles("test")
class HibernateDemoApplicationTests(@Autowired val recipeService: RecipeService) {

    @Test
    fun `basic entity checks`() {
        val p = Recipe(1, "desc", "title", "wesd", setOf(
                RecipeIngredient(1, Ingredient(1, "meat"), MeasurementUnit(1, "pieces"), 20)),
        Date.from(now()), Author(1, "me", "me@email.com"))
        assertThat(recipeService.getAllRecipes()).hasSize(0)
        recipeService.addRecipe(p)
        assertThat(recipeService.getAllRecipes()).hasSize(1)
        val result = recipeService.getRecipeById(1).get()
        assertThat(p).isEqualTo(result)
    }
}