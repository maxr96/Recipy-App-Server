package com.recipeapp.recipeserver.dto.external

data class ExternalInstruction(
    val name: String,
    val steps: List<ExternalStep>,
)
