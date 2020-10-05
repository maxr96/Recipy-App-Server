package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.*
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
class HibernateDemoApplicationTests(@Autowired val repo: RecipeRepository) {

    @Test
    fun `basic entity checks`() {
        val p = Recipe(1, "desc", "title", "wesd", setOf(
                RecipeIngredient(1, Ingredient(1, "meat"), MeasurementUnit(1, "pieces"), 20), ),
        Date.from(now()), Author(1, "me", "me@email.com"))
        assertThat(repo.findAll()).hasSize(0)
        repo.save(p)
        assertThat(repo.findAll()).hasSize(1)
//        val result = repo.getById(1)
//        assertThat(p).isEqualTo(result)
    }
}