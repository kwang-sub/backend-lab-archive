package com.example.ssiach5ex3.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ProjectConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic { c ->
            run {
                c.realmName("OTHER")
                c.authenticationEntryPoint(CustomEntryPoint())
            }
        }
        http.authorizeHttpRequests().anyRequest().authenticated()
        return http.build()
    }
}