package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.MeasurmentUnit
import com.recipeapp.recipeserver.repository.UnitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UnitServiceImpl : UnitService {
    @Autowired
    private lateinit var unitRepository: UnitRepository

    override fun addUnit(unit: MeasurmentUnit): MeasurmentUnit {
        return unitRepository.save(unit);
    }
}