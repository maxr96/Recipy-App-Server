package com.recipeapp.recipeserver.dto

import com.recipeapp.recipeserver.model.MeasurementUnit

data class MeasurementUnitDTO(val name: String)

fun MeasurementUnit.mapToDto(): MeasurementUnitDTO {
    return MeasurementUnitDTO(
            this.name
    )
}

fun MeasurementUnitDTO.mapToEntity(): MeasurementUnit {
    return MeasurementUnit(
            name = this.name
    )
}