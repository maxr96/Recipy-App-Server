package com.recipeapp.recipeserver.controller

import com.recipeapp.recipeserver.model.MeasurementUnit
import com.recipeapp.recipeserver.service.UnitService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/units")
class UnitController (
        private val unitService: UnitService
) {
    @PostMapping
    fun postUnit(@RequestBody unit: MeasurementUnit): ResponseEntity<MeasurementUnit> {
        val addedUnit = unitService.addUnit(unit)
        val location: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(addedUnit.id).toUri()
        return ResponseEntity.created(location).build()
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