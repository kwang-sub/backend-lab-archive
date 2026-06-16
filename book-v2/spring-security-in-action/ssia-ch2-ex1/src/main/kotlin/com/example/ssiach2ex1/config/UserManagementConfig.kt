package com.example.ssiach2ex1.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
class UserManagementConfig {

    @Bean
    fun userDetailsService(): UserDetailsService {
        val userDetailsService = InMemoryUserDetailsManager()
        val user = User.withUsername("john")
            .password("12345")
            .authorities("read")
            .build()
        userDetailsService.createUser(user)

        return userDetailsService
    }

    @Bean
    fun passwordEncoder() : PasswordEncoder = NoOpPasswordEncoder.getInstance()
}