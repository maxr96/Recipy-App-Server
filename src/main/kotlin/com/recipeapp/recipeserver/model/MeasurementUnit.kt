package com.recipeapp.recipeserver.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "Units", uniqueConstraints = [UniqueConstraint(columnNames = ["name"], name = "uniqueUnitNameConstraint")])
class MeasurementUnit(
    @Id
    @GeneratedValue
    @Column
    var id: Short = 0,

    @Column(length = 50, unique = true, nullable = false)
    var name: String,
)
