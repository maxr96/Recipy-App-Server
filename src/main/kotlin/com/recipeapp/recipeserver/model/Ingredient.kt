package com.recipeapp.recipeserver.model

import javax.persistence.*

@Entity
@Table(name = "Ingredients", uniqueConstraints = [UniqueConstraint(columnNames = ["name"], name = "uniqueIngredientNameConstraint")])
class Ingredient(
        @Id
        @GeneratedValue
        @Column
        var id: Int = 0,

        @Column(length = 50, nullable = false)
        var name: String
)