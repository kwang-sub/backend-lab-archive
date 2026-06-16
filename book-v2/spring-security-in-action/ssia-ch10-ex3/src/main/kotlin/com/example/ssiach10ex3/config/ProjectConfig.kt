package com.example.ssiach10ex3.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ProjectConfig {

    @Bean
    fun securityFilterChane(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.ignoringRequestMatchers("/ciao") }
            .authorizeHttpRequests().anyRequest().permitAll()

        return http.build()
    }
}