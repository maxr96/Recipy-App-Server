package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.*
import com.recipeapp.recipeserver.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

@SpringBootTest
@ActiveProfiles("test")
class RecipeServiceTests(@Autowired val recipeService: RecipeService, @Autowired val userRepository: UserRepository) {

    @BeforeEach
    fun addUserToDb() {
        userRepository.save(User(username = "me", email = "me@email.com"))
    }

    @AfterEach
    fun flush() {
        userRepository.flush()
    }

    @Test
    @Transactional
    fun `add, retrieve and delete recipes`() {
        val p = Recipe(
            1, "desc", "title", Category.MAIN_DISH, "wesd", "",
            time = Duration.ZERO, author = User(1, "me", "me@email.com"),
            tags = mutableSetOf(Tag(1, "healthy")),
        )
        p.addRecipeIngredient(RecipeIngredient(1, Ingredient(1, "meat"), MeasurementUnit(1, "pieces"), 20.0, p))
        assertThat(recipeService.getAllRecipes()).hasSize(0)
        recipeService.addRecipe(p)
        val recipes = recipeService.getAllRecipes()
        assertThat(recipes).hasSize(1)
        val storedRecipe = recipes.first()
        assertThat(p.title).isEqualTo(storedRecipe.title)
        assertThat(p.description).isEqualTo(storedRecipe.description)
        val storedRecipeOne = recipeService.getRecipeById(storedRecipe.id)
        assertThat(storedRecipe).usingRecursiveComparison().isEqualTo(storedRecipeOne.get())
        assertThat(p.recipeIngredients.first().amount).isEqualTo(storedRecipe.recipeIngredients.first().amount)
        recipeService.deleteRecipe(storedRecipe.id)
        assertThat(recipeService.getAllRecipes().isEmpty())
    }

    @Test
    @Transactional
    fun `add new recipe with duplicated unit and ingredient names`() {
        val p = Recipe(
            1, "desc", "title", Category.MAIN_DISH, "wesd", "instr",
            time = Duration.ZERO, author = User(1, "me", "me@email.com"),
            tags = mutableSetOf(Tag(1, "healthy")),
        )
        p.addRecipeIngredient(RecipeIngredient(1, Ingredient(1, "meat"), MeasurementUnit(1, "pieces"), 20.0, p))
        assertThat(recipeService.getAllRecipes()).hasSize(0)
        recipeService.addRecipe(p)
        assertThat(recipeService.getAllRecipes()).hasSize(1)

        val p2 = Recipe(
            1, "desc", "title", Category.MAIN_DISH, "wesd", "instr",
            time = Duration.ZERO, author = User(1, "me", "me@email.com"),
            tags = mutableSetOf(Tag(1, "healthy")),
        )
        p2.addRecipeIngredient(RecipeIngredient(1, Ingredient(1, "meat"), MeasurementUnit(1, "pieces"), 20.0, p2))
        recipeService.addRecipe(p2)
        assertThat(recipeService.getAllRecipes()).hasSize(2)
    }
}
