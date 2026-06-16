package com.example.ssiach5ex1.config

import com.example.ssiach5ex1.provider.CustomAuthenticationProvider
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
    fun authenticationProvider(): CustomAuthenticationProvider {
        return CustomAuthenticationProvider(passwordEncoder(), userDetailsService())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

    @Bean
    fun userDetailsService(): UserDetailsService {
        val userDetails = User.withUsername("bill")
            .password("12345")
            .authorities("read", "write")
            .build()
        val userDetailsService = InMemoryUserDetailsManager()
        userDetailsService.createUser(userDetails)
        return userDetailsService
    }

    @Bean
    fun securityFilterChane(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic()
        http.userDetailsService(userDetailsService())
        http.authenticationProvider(authenticationProvider())
        http.authorizeHttpRequests().anyRequest().authenticated()
        return http.build()
    }
}