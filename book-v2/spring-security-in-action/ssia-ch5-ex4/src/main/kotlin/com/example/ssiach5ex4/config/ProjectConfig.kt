package com.example.ssiach5ex4.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ProjectConfig(
    private val successHandler: CustomAuthenticationSuccessHandler,
    private val failureHandler: CustomAuthenticationFailureHandler
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

    @Bean
    fun securityFilterChane(http: HttpSecurity): SecurityFilterChain {
//        http.formLogin()
//            .defaultSuccessUrl("/home", true)

        val user = User("kwang", "12345", mutableSetOf(GrantedAuthority { "read" }))
        val hh: UserDetailsService = InMemoryUserDetailsManager(user)
        http.formLogin()
            .successHandler(successHandler)
            .failureHandler(failureHandler)
            .and()
            .httpBasic()
            .and()
            .userDetailsService(hh)

        http.authorizeHttpRequests().anyRequest().authenticated()
        return http.build()
    }
}