package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {
    fun findFirstByUsername(username: String): User?
}