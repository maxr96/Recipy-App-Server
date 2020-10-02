package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.MeasurementUnit
import org.springframework.data.jpa.repository.JpaRepository

internal interface UnitRepository: JpaRepository<MeasurementUnit, Long> {
    fun getById(id: Long): Unit?
}
