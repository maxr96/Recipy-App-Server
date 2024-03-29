package com.recipeapp.recipeserver.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Duration
import java.time.Instant
import java.util.Date
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.Lob
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.Temporal
import javax.persistence.TemporalType
import kotlin.collections.HashSet

@Entity
@Table(name = "Recipes")
class Recipe(
    @Id
    @GeneratedValue
    @Column
    var id: Int = 0,

    @Column(length = 50, nullable = false)
    var title: String,

    @Column
    @Lob
    var description: String,

    @Enumerated(EnumType.ORDINAL)
    var category: Category,

    @Column(length = 60)
    var cuisine: String,

    @Column
    @Lob
    var instructions: String,

    @Column(length = 255)
    var imagePath: String = "",

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "recipe")
    var recipeIngredients: MutableSet<RecipeIngredient> = HashSet(),

    @Column(nullable = false)
    var time: Duration,

    // additional tags, like vegetarian, vegan, healthy, cheap, etc.
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.MERGE, CascadeType.PERSIST],
    )
    @JoinTable(
        name = "recipe_tags",
        joinColumns = [JoinColumn(name = "recipe_id")],
        inverseJoinColumns = [ JoinColumn(name = "tag_id") ],
    )
    var tags: MutableSet<Tag>,

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
    var updateDate: Date = Date.from(Instant.now()),

    // Only used for external recipes
    @Column(length = 255)
    var creditsText: String = "",
) {
    fun addRecipeIngredient(ingredient: RecipeIngredient) {
        this.recipeIngredients.add(ingredient)
        ingredient.recipe = this
    }
}
