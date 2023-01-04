package com.recipeapp.recipeserver.dto

import com.recipeapp.recipeserver.model.User

data class UserDTO(val username: String, val email: String)

fun User.mapToDto(): UserDTO {
    return UserDTO(
        this.username,
        this.email,
    )
}
