package com.recipeapp.recipeserver.model

import javax.persistence.*

@Entity
@Table(name = "Units", uniqueConstraints = [UniqueConstraint(columnNames = ["name"], name = "uniqueUnitNameConstraint")])
class MeasurementUnit(
    @Id
    @GeneratedValue
    @Column
    var id: Int = 0,

    @Column(length = 50, unique = true, nullable = false)
    var name: String
)
