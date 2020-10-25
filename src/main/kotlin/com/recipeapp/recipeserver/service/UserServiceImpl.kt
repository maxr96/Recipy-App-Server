package com.recipeapp.recipeserver.service

import com.recipeapp.recipeserver.model.User
import com.recipeapp.recipeserver.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserServiceImpl (val userRepository: UserRepository) : UserService  {
    override fun getFirstByName(username: String): User? {
        return userRepository.findByUsername(username);
    }
}