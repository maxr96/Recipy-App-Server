package com.recipeapp.recipeserver.controller

import com.recipeapp.recipeserver.model.User
import com.recipeapp.recipeserver.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sign-up")
class SignupController(val userRepository: UserRepository, val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    @PostMapping
    fun signUp(@RequestBody user: User) {
        user.password = bCryptPasswordEncoder.encode(user.password)
        userRepository.save(user)
    }
}