package com.recipeapp.recipeserver.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@Configuration
@EnableWebSecurity
class AppSecurityConfig : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/", "/recipes", "/recipes/{^[\\\\d]\$}", "/units").permitAll()
        .antMatchers(HttpMethod.POST, "/units", "/recipes").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()

                ///.formLogin()
                //.permitAll().and().logout().permitAll()
    }
}