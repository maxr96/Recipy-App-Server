package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.Recipe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@ActiveProfiles("dev")
class HibernateDemoApplicationTests(@Autowired val repo: RecipeRepository) {

    @Test
    fun `basic entity checks`() {
       // val ingredients =  hashSetOf(Ingredient(223, "name"));
        val p = Recipe(123, "desc", "title", 1230, "me");
        assertThat(repo.findAll()).hasSize(2)
//        repo.save(p)
//        assertThat(repo.findAll()).hasSize(1)
//        assertThat(p).isEqualTo(repo.getById(123))
    }
}