package com.recipeapp.recipeserver.model

import javax.persistence.*

@Entity
class Recipe (
        @get:Id
        @get:GeneratedValue
        @get:Column
        var id: Long,

        @get:Column
        var title: String,

        @get:Column
        var description: String,

        @get:Column
        var instructions: String,

       @get:OneToMany
        var ingredientAmounts: Set<RecipeIngredient>,
        var time: Int,
        var author: String)

@Entity
class RecipeIngredient (
        @get:Id
        @get:GeneratedValue
        @get:Column
        var id: Long,

        @get:ManyToOne
        @get:JoinColumn(name = "recipe_id", nullable = false)
        var recipe: Recipe,

        @get:OneToOne(cascade = [CascadeType.ALL])
        @get:JoinColumn(name = "ingredient_id")
        var ingredient: Ingredient,

        @get:OneToOne(cascade = [CascadeType.ALL])
        @get:JoinColumn(name = "measure_id")
        var unit: MeasurementUnit,

        @get:Column
        var amount: Int
        )
@Entity
class Ingredient(
        @get:Id
        @get:GeneratedValue
        @get:Column
        var id: Long,

        @get:Column
        var name: String
)

@Entity
class MeasurementUnit(
        @get:Id
        @get:GeneratedValue
        @get:Column
        var id: Long,

        @get:Column
        var name: String
)