package com.recipeapp.recipeserver.model

import javax.persistence.*

@Entity
class MeasurementUnit(
        @Id
        @GeneratedValue
        @Column
        var id: Int,

        @Column(length = 50, unique = true, nullable = false)
        var name: String
)