package com.recipeapp.recipeserver.model

import javax.persistence.*

@Entity
@Table(name = "Tags", uniqueConstraints = [UniqueConstraint(columnNames = ["name"], name = "uniqueTagNameConstraint")])
class Tag(
    @Id
    @GeneratedValue
    @Column
    var id: Short = 0,

    @Column(length = 50, unique = true, nullable = false)
    var name: String,
)
