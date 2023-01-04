package com.recipeapp.recipeserver.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "Ingredients", uniqueConstraints = [UniqueConstraint(columnNames = ["name"], name = "uniqueIngredientNameConstraint")])
class Ingredient(
    @Id
    @GeneratedValue
    @Column
    var id: Int = 0,

    @Column(length = 50, nullable = false)
    var name: String,
)
