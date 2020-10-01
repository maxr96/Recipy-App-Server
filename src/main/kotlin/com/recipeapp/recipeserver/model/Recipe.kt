package com.recipeapp.recipeserver.model

import javax.persistence.*

@Entity
class Recipe (
        @get:Id
        @get:GeneratedValue
        @get:Column
        var id: Long,

        @get:Column
        var description: String,

        @get:Column
        var title: String,

       @get:OneToMany()
        var ingredientAmounts: Set<IngredientAmount>,
        var time: Int,
        var author: String)

@Entity
class IngredientAmount (
        @get:Id
        @get:GeneratedValue
        @get:Column
        var id: Long,

        @get:ManyToOne
        @get:JoinColumn(nullable = false)
        var recipe: Recipe,

        @get:OneToOne(cascade = [CascadeType.ALL])
        @get:JoinColumn
        var ingredient: Ingredient,

        @get:OneToOne(cascade = [CascadeType.ALL])
        @get:JoinColumn
        var unit: MeasurmentUnit,

        )
@Entity
class Ingredient(
        @get:Id
        @get:GeneratedValue
        @get:Column
        var id: Int,

        @get:Column
        var name: String,

        @get:OneToOne
        var ingredientAmount: IngredientAmount
)

@Entity
class MeasurmentUnit(
        @get:Id
        @get:GeneratedValue
        @get:Column
        var id: Int,

        @get:Column
        var name: String,

        @get:OneToOne
        var ingredientAmount: IngredientAmount
)