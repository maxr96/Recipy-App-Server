package com.recipeapp.recipeserver.model

import java.util.*
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

        @Column
        @Temporal(TemporalType.TIMESTAMP)
        var time: Date,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(nullable = false, foreignKey = ForeignKey(name = "FK_recipes_author"))
        var author: User)