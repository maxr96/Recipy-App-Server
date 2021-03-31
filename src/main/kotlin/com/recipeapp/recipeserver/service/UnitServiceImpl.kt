package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.MeasurementUnit
import com.recipeapp.recipeserver.repository.UnitRepository
import org.springframework.stereotype.Component

@Component
class UnitServiceImpl(val unitRepository: UnitRepository) : UnitService {

    override fun addUnit(unit: MeasurementUnit): MeasurementUnit {
        return unitRepository.save(unit)
    }

    override fun getAllUnits(): List<MeasurementUnit> {
        return unitRepository.findDistinctByNameIsNotIn(emptyList())
    }

    override fun getAllByNames(names: List<String>): List<MeasurementUnit?> {
        return unitRepository.findAllByNameIn(names)
    }
}
