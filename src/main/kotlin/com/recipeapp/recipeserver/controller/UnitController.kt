package com.recipeapp.recipeserver.controller

import com.recipeapp.recipeserver.dto.MeasurementUnitDTO
import com.recipeapp.recipeserver.dto.mapToDto
import com.recipeapp.recipeserver.dto.mapToEntity
import com.recipeapp.recipeserver.service.UnitService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI

@RestController
@RequestMapping("/units")
class UnitController(
    private val unitService: UnitService,
) {
    @PostMapping
    fun postUnit(@RequestBody unit: MeasurementUnitDTO): ResponseEntity<MeasurementUnitDTO> {
        val addedUnit = unitService.addUnit(unit.mapToEntity())
        val location: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(addedUnit.id).toUri()
        return ResponseEntity.created(location).build()
    }

    @GetMapping
    fun getUnits(): ResponseEntity<List<MeasurementUnitDTO>> {
        val units = unitService.getAllUnits().map { it.mapToDto() }
        return if (units.isNotEmpty()) {
            return ResponseEntity.ok().body(units)
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
