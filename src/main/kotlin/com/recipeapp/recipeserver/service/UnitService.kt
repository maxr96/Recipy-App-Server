package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.MeasurementUnit

interface UnitService {
    fun addUnit(unit: MeasurementUnit): MeasurementUnit
}