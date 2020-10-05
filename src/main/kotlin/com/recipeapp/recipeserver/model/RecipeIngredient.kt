package com.recipeapp.recipeserver.model

import javax.persistence.*

@Entity
class RecipeIngredient (
        @Id
        @GeneratedValue
        @Column
        var id: Int,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(nullable = false, foreignKey = ForeignKey(name = "FK_ingredients_recipeingredient"))
        var ingredient: Ingredient,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(nullable = false, foreignKey = ForeignKey(name = "FK_units_recipeingredient"))
        var unit: MeasurementUnit,

        @Column
        var amount: Short
)