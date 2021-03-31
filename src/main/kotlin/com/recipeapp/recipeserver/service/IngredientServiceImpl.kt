package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.Ingredient
import com.recipeapp.recipeserver.repository.IngredientRepository
import org.springframework.stereotype.Component

@Component
class IngredientServiceImpl(var ingredientRepository: IngredientRepository) : IngredientService {
    override fun getAllByNames(names: List<String>): List<Ingredient?> {
        return ingredientRepository.findAllByNameIn(names)
    }
}
