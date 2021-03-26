package com.recipeapp.recipeserver.config

import com.recipeapp.recipeserver.security.JWTAuthenticationFilter
import com.recipeapp.recipeserver.security.JWTAuthorizationFilter
import com.recipeapp.recipeserver.security.SIGN_UP_URL
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class AppSecurityConfig(@Qualifier("userDetailsServiceImpl") val userDetailsService: UserDetailsService) : WebSecurityConfigurerAdapter() {

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/login", SIGN_UP_URL).permitAll()
            .antMatchers(HttpMethod.GET, "/recipes**").permitAll()
            .antMatchers(HttpMethod.GET, "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/swagger-resources", "/swagger-resources/*").permitAll()
            .antMatchers(HttpMethod.GET, "/admin").hasAuthority("ADMIN")
            .anyRequest().authenticated()
            .and()
            .addFilter(JWTAuthenticationFilter(authenticationManager()))
            .addFilter(JWTAuthorizationFilter(authenticationManager()))
            // this disables session creation on Spring Security
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
    }
}
