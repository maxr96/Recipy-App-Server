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

//        @get:OneToMany
//        var ingredients: Set<Ingredient>,
        var time: Int,
        var author: String)
//@Entity
//class Ingredient(
//        @get:Id
//        @get:GeneratedValue
//        @get:Column
//        var id: Int,
//
//        @get:Column
//        var name: String,
//)