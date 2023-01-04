package com.recipeapp.recipeserver.dto

import com.recipeapp.recipeserver.model.MeasurementUnit
import javax.validation.constraints.Size

data class MeasurementUnitDTO(
    @Size(min = 2, max = 50)
    val name: String)

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
