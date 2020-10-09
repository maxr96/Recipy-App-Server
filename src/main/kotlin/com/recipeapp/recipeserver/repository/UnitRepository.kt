package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.MeasurementUnit
import org.springframework.data.jpa.repository.JpaRepository

internal interface UnitRepository: JpaRepository<MeasurementUnit, Int> {
    fun findDistinctByNameIsNotIn(names: Collection<String>): List<MeasurementUnit>

    fun findFirstByName(name: String): MeasurementUnit?
}
