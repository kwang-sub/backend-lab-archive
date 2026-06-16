package com.example.ssiach8ch1.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
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
        val inMemoryUserDetailsManager = InMemoryUserDetailsManager()

        val user1 = User.withUsername("kwang")
            .password("1234")
            .authorities("read")
            .build()

        val user2 = User.withUsername("sub")
            .password("1234")
            .authorities("read", "premium")
            .build()

        inMemoryUserDetailsManager.createUser(user1)
        inMemoryUserDetailsManager.createUser(user2)

        return inMemoryUserDetailsManager
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

    @Bean
    fun securityFilterChane(http: HttpSecurity): SecurityFilterChain {
        http.formLogin()
        http.csrf().disable()
        http
            .authorizeHttpRequests().requestMatchers("/product/{code:^[0-9]*$}").permitAll()
            .and()
            .authorizeHttpRequests().requestMatchers("/hello").authenticated()
            .anyRequest().denyAll()


        return http.build()
    }
}