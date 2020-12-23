package com.recipeapp.recipeserver.repository

import com.recipeapp.recipeserver.model.Role
import com.recipeapp.recipeserver.model.RoleName
import org.springframework.data.jpa.repository.JpaRepository

public interface RoleRepository : JpaRepository<Role, Int> {
    fun findByName(roleName: RoleName): Role?
}
