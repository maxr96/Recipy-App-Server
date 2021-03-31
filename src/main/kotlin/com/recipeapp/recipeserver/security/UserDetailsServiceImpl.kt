package com.recipeapp.recipeserver.security

import com.recipeapp.recipeserver.model.AppUserDetails
import com.recipeapp.recipeserver.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.recipeapp.recipeserver.model.User as AppUser

@Service
class UserDetailsServiceImpl(val userRepository: UserRepository) : UserDetailsService {
    @Transactional(readOnly = true)
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findFirstByUsername(username) ?: throw UsernameNotFoundException(username)
        return AppUserDetails(user)
    }

    fun save(user: AppUser) {
        userRepository.save(user)
    }
}
