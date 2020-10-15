package com.recipeapp.recipeserver.model

import java.util.*
import javax.persistence.*

@Entity
class Recipe (
        @Id
        @GeneratedValue
        @Column
        var id: Int = 0,

        @Column(length = 50, nullable = false)
        var title: String,

        @Column(length = 200)
        var description: String,

        @Column
        @Lob
        var instructions: String,

        @OneToMany(targetEntity = RecipeIngredient::class, cascade = [CascadeType.ALL],
                fetch = FetchType.LAZY, orphanRemoval = true)
        @JoinColumn(name = "recipeId", referencedColumnName = "id",
                foreignKey = ForeignKey(name = "FK_recipe_recipeingredients"))
        var recipeIngredients: Set<RecipeIngredient>,

        @Column(nullable = false)
        @Temporal(TemporalType.TIMESTAMP)
        var time: Date,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(nullable = false, foreignKey = ForeignKey(name = "FK_recipes_author"))
        var author: User)