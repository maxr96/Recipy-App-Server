package com.recipeapp.recipeserver.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.recipeapp.recipeserver.model.AppUserDetails
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.security.Key
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import com.recipeapp.recipeserver.model.User as ApplicationUser

class JWTAuthenticationFilter(authManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {
    init {
        authenticationManager = authManager
    }

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse,
    ): Authentication {
        val creds = ObjectMapper()
            .readValue(req.inputStream, ApplicationUser::class.java)
        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                creds.username,
                creds.password,
                emptyList<GrantedAuthority>(),
            ),
        )
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse,
        chain: FilterChain?,
        auth: Authentication,
    ) {
        val keyBytes = Decoders.BASE64.decode(SECRET)
        val key: Key = Keys.hmacShaKeyFor(keyBytes)
        val expirationTime = environment.getProperty("EXPIRATION_TIME") ?: EXPIRATION_TIME
        val jwt = Jwts.builder()
            .setSubject((auth.principal as AppUserDetails).username)
            .setExpiration(Date(System.currentTimeMillis() + expirationTime.toLong()))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
        res.addHeader("access-control-expose-headers", HEADER_STRING)
        res.addHeader(HEADER_STRING, "$TOKEN_PREFIX $jwt")
    }
}
