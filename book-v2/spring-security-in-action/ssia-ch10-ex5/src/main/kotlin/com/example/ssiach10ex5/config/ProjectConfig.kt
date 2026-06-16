package com.example.ssiach10ex5.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource

@Configuration
class ProjectConfig {

    @Bean
    fun securityFilterChane(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()
            .authorizeHttpRequests().anyRequest().permitAll()

        http.cors {
            val source = CorsConfigurationSource { request ->
                val config = CorsConfiguration()
                config.allowedOrigins = listOf("example.com", "example.org", "http://localhost:8080")
                config.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
                return@CorsConfigurationSource config
            }
            it.configurationSource(source)
        }

        return http.build()
    }
}