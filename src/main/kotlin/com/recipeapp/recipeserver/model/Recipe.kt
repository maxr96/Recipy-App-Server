package com.recipeapp.recipeserver.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet

@Entity
@Table(name = "Recipes")
class Recipe (
        @Id
        @GeneratedValue
        @Column
        var id: Int = 0,

        @Column(length = 50, nullable = false)
        var title: String,

        @Column(length = 200)
        var description: String,

        @Enumerated(EnumType.ORDINAL)
        var category: Category,

        @Column
        @Lob
        var instructions: String,

        @Column(length = 255)
        var imagePath: String = "",

        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "recipe")
        var recipeIngredients: MutableSet<RecipeIngredient> = HashSet(),

        @Column(nullable = false)
        @Temporal(TemporalType.TIMESTAMP)
        var time: Date,

        @ManyToOne(cascade = [CascadeType.ALL])
        @JoinColumn(nullable = false, name = "author_id")
        var author: User,

        @CreationTimestamp
        @Column(updatable = false)
        @Temporal(TemporalType.TIMESTAMP)
        var createDate: Date = Date.from(Instant.now()),

        @UpdateTimestamp
        @Column
        @Temporal(TemporalType.TIMESTAMP)
        var updateDate: Date = Date.from(Instant.now()))
{
        fun addRecipeIngredient(ingredient: RecipeIngredient) {
                this.recipeIngredients.add(ingredient)
                ingredient.recipe = this
        }
}