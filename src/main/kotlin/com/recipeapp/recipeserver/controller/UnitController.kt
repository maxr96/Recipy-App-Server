package com.recipeapp.recipeserver.controller

import com.recipeapp.recipeserver.model.MeasurementUnit
import com.recipeapp.recipeserver.service.UnitService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/units")
class UnitController (
        private val unitService: UnitService
) {
    @PostMapping
    fun postUnit(@RequestBody unit: MeasurementUnit): ResponseEntity<MeasurementUnit> {
        return ResponseEntity.ok().body(unitService.addUnit(unit))
    }

    @GetMapping
    fun getUnits(): ResponseEntity<List<MeasurementUnit>> {
        val units = unitService.getAllUnits()
        return if (units.isNotEmpty()) {
            return ResponseEntity.ok().body(units)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}