package com.recipeapp.recipeserver.model

import javax.persistence.*

@Entity
class Recipe (
        @Id
        @GeneratedValue
        @Column
        var id: Int,

        @Column(length = 50)
        var title: String,

        @Column(length = 200)
        var description: String,

        @Column
        var instructions: String,

        @OneToMany(targetEntity = RecipeIngredient::class, cascade = [CascadeType.ALL],
                fetch = FetchType.LAZY, orphanRemoval = true)
        @JoinColumn(name = "recipeId", referencedColumnName = "id",
                foreignKey = ForeignKey(name = "FK_recipe_recipeingredients"))
        var recipeIngredients: Set<RecipeIngredient>,
        var time: Int,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(nullable = false, foreignKey = ForeignKey(name = "FK_recipes_author"))
        var author: Author)

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
@Entity
class Ingredient(
        @Id
        @GeneratedValue
        @Column
        var id: Int,

        @Column(length = 50)
        var name: String
)

@Entity
class MeasurementUnit(
        @Id
        @GeneratedValue
        @Column
        var id: Int,

        @Column(length = 50)
        var name: String
)