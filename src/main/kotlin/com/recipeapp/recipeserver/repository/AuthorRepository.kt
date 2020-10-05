package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.Author
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorRepository: JpaRepository<Author, Long> {
}