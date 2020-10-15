package com.recipeapp.recipeserver.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Ingredient(
        @Id
        @GeneratedValue
        @Column
        var id: Int = 0,

        @Column(length = 50, unique = true, nullable = false)
        var name: String
)