package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.MeasurementUnit
import com.recipeapp.recipeserver.repository.UnitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UnitServiceImpl : UnitService {
    @Autowired
    private lateinit var unitRepository: UnitRepository

    override fun addUnit(unit: MeasurementUnit): MeasurementUnit {
        return unitRepository.save(unit);
    }

    override fun getAllUnits(): List<MeasurementUnit> {
        return unitRepository.findAll();
    }
}