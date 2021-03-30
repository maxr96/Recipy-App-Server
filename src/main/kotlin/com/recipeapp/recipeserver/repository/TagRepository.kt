package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.Tag
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Short> {
    fun findAllByNameIn(name: List<String>): List<Tag?>
}
