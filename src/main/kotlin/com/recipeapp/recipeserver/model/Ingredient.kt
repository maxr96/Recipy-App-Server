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
        var id: Int,

        @Column(length = 50)
        var name: String
)