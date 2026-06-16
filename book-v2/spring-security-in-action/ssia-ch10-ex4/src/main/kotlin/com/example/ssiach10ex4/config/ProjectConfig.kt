package com.example.ssiach10ex4.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CsrfTokenRepository

@Configuration
class ProjectConfig(
    private val customTokenRepository: CsrfTokenRepository
) {


    @Bean
    fun securityFilterChane(http: HttpSecurity): SecurityFilterChain {
        http.csrf {
            it.csrfTokenRepository(customTokenRepository)
            it.ignoringRequestMatchers("/ciao")
        }.authorizeHttpRequests().anyRequest().permitAll()

        return http.build()
    }
}