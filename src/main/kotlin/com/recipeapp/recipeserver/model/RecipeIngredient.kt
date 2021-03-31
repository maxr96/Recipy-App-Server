package com.recipeapp.recipeserver.model

import javax.persistence.*

@Entity
@Table(name = "RecipeIngredients")
class RecipeIngredient(
    @Id
    @GeneratedValue
    @Column
    var id: Int = 0,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(nullable = false, name = "ingredient_id")
    var ingredient: Ingredient,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(nullable = false, name = "unit_id")
    var unit: MeasurementUnit,

    @Column
    var amount: Short,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    var recipe: Recipe
)
