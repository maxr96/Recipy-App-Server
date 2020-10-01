package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.MeasurmentUnit
import org.springframework.data.jpa.repository.JpaRepository

internal interface UnitRepository: JpaRepository<MeasurmentUnit, Long> {
    fun getById(id: Long): Unit?
}
