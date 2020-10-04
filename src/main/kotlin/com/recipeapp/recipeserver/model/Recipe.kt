package com.recipeapp.recipeserver.model

import javax.persistence.*

@Table
@Entity
class Recipe (
        @Id
        @GeneratedValue
        @Column
        var id: Long,

        @Column
        var title: String,

        @Column
        var description: String,

        @Column
        var instructions: String,

       @OneToMany
        var recipeIngredients: MutableCollection<RecipeIngredient>,
        var time: Int,
        var author: String)

@Entity
class RecipeIngredient (
        @Id
        @GeneratedValue
        @Column
        var id: Long,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(nullable = false)
        var ingredient: Ingredient,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(nullable = false)
        var unit: MeasurementUnit,

        @Column
        var amount: Int
        )
@Entity
class Ingredient(
        @Id
        @GeneratedValue
        @Column
        var id: Long,

        @Column
        var name: String
)

@Entity
class MeasurementUnit(
        @Id
        @GeneratedValue
        @Column
        var id: Long,

        @Column
        var name: String
)