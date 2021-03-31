package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.MeasurementUnit
import org.springframework.data.jpa.repository.JpaRepository

interface UnitRepository : JpaRepository<MeasurementUnit, Int> {
    fun findDistinctByNameIsNotIn(names: Collection<String>): List<MeasurementUnit>

    fun findAllByNameIn(name: List<String>): List<MeasurementUnit?>
}
