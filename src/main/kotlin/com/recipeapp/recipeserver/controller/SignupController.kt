package com.recipeapp.recipeserver.controller

import com.recipeapp.recipeserver.model.User
import com.recipeapp.recipeserver.repository.UserRepository
import com.recipeapp.recipeserver.security.SIGN_UP_URL
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.SQLException
import java.util.regex.Pattern

@RestController
@RequestMapping
class SignupController(val userRepository: UserRepository, val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    companion object {
        val PASSWORD_PATTERN: Pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$")
    }
    @PostMapping(SIGN_UP_URL)
    fun signUp(@RequestBody user: User): ResponseEntity<String> {
        val m = PASSWORD_PATTERN.matcher(user.password)
        if (!m.find()) {
            return ResponseEntity("INVALID_PASSWORD", HttpStatus.BAD_REQUEST)
        }
        user.password = bCryptPasswordEncoder.encode(user.password)

        try {
            userRepository.saveAndFlush(user)
        } catch (s: DataIntegrityViolationException) {
            val rootCause = s.cause?.cause
            if (rootCause is SQLException) {
                val rootCauseMessage = rootCause.message ?: ""
                return when {
                    rootCauseMessage.contains("uniqueEmailConstraint") -> ResponseEntity("EMAIL_EXISTS", HttpStatus.BAD_REQUEST)
                    rootCauseMessage.contains("uniqueUserNameConstraint") -> ResponseEntity("USERNAME_EXISTS", HttpStatus.BAD_REQUEST)
                    else -> ResponseEntity(HttpStatus.BAD_REQUEST)
                }
            }
        }
        return ResponseEntity(HttpStatus.CREATED)
    }
}
