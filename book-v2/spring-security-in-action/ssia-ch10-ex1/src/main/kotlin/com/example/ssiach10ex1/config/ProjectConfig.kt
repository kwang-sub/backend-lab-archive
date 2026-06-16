package com.example.ssiach10ex1.config

import com.example.ssiach10ex1.filter.CsrfTokenLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.csrf.CsrfFilter

@Configuration
class ProjectConfig {

    @Bean
    fun securityFilterChane(http: HttpSecurity): SecurityFilterChain {
        http.addFilterAfter(CsrfTokenLogger(), CsrfFilter::class.java)
            .authorizeHttpRequests().anyRequest().permitAll()

        return http.build()
    }
}