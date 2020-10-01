package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.MeasurmentUnit

interface UnitService {
    fun addUnit(unit: MeasurmentUnit): MeasurmentUnit
}