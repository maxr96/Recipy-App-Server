package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.User

interface UserService {
    fun getFirstByName(username: String): User?
}