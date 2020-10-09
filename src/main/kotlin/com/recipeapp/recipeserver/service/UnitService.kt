package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.MeasurementUnit

interface UnitService {
    fun getAllUnits(): List<MeasurementUnit>
    fun addUnit(unit: MeasurementUnit): MeasurementUnit
    fun getOneByName(name: String): MeasurementUnit?
}