package com.example.ssiach2ex1.config

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


//@Configuration
class ProjectConfig {
    //    @Bean
    fun userDetailsService(): UserDetailsService {
        val userDetailsService = InMemoryUserDetailsManager()
        val user = User.withUsername("kwang")
            .password(passwordEncoder().encode("12345"))
            .authorities("read")
            .build()
        userDetailsService.createUser(user)

        return userDetailsService
    }

    //    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


    //    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        val inMemoryUserDetailsManager = InMemoryUserDetailsManager()
        val user = User.withUsername("kwang")
            .password(passwordEncoder().encode("12345"))
            .authorities("read")
            .build()
        inMemoryUserDetailsManager.createUser(user)
        http.userDetailsService(inMemoryUserDetailsManager)

        http.httpBasic()
        http.authorizeHttpRequests()
            .anyRequest().authenticated()

        return http.build()
    }

    //    @Bean
    fun customAuthenticationProvider(): AuthenticationProvider {
        return CustomAuthenticationProvider()
    }

    //    @Bean
    fun filterChainV2(http: HttpSecurity): SecurityFilterChain? {
        http.httpBasic()
        http.authorizeHttpRequests().anyRequest().authenticated()

        return http.build()
    }

}