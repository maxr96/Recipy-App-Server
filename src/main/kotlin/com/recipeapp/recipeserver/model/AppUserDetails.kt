package com.recipeapp.recipeserver.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*


class AppUserDetails(private val user: User) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        val roles: Set<Role> = user.roles
        val authorities: MutableList<SimpleGrantedAuthority?> = ArrayList()
        for (role in roles) {
            authorities.add(SimpleGrantedAuthority(role.name.displayName))
        }
        return authorities
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}