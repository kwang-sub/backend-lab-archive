package com.example.ssiach10ex2.config

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
    fun uds(): UserDetailsService {
        val detailsManager = InMemoryUserDetailsManager()
        val u1 = User.withUsername("kwang")
            .password("12345")
            .authorities("READ")
            .build()
        detailsManager.createUser(u1)

        return detailsManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

    @Bean
    fun securityFilterChane(http: HttpSecurity): SecurityFilterChain {
        http.formLogin()
            .defaultSuccessUrl("/main", true)
            .and()
            .authorizeHttpRequests().anyRequest().authenticated()

        return http.build()
    }
}