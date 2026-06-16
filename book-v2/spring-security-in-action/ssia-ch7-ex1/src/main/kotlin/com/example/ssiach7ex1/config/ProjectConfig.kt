package com.example.ssiach7ex1.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ProjectConfig {

    @Bean
    fun userDetailsService(): UserDetailsService {
        val userDetailsManager = InMemoryUserDetailsManager()

        val user = User.withUsername("john")
            .password("1234")
            .authorities("ROLE_ADMIN")
            .build()

        val user2 = User.withUsername("jane")
            .password("1234")
            .authorities("ROLE_MANAGER")
            .build()

        userDetailsManager.createUser(user)
        userDetailsManager.createUser(user2)

        return userDetailsManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

    @Bean
    fun securityFilterChane(http: HttpSecurity): SecurityFilterChain {
        http.formLogin()

        http.authorizeHttpRequests().anyRequest().hasRole("ADMIN")

        return http.build()
    }

}
