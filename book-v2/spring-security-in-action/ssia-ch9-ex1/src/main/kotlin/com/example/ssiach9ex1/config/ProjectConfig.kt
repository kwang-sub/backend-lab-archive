package com.example.ssiach9ex1.config

import com.example.ssiach9ex1.filter.AuthenticationLoggingFilter
import com.example.ssiach9ex1.filter.RequestValidationFilter
import com.example.ssiach9ex1.filter.StaticKeyAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
class ProjectConfig(
    private val staticKeyAuthenticationFilter: StaticKeyAuthenticationFilter
) {

    @Bean
    fun securityFilterChane(http: HttpSecurity): SecurityFilterChain {
        http
//            .addFilterBefore(RequestValidationFilter(), BasicAuthenticationFilter::class.java)
//            .addFilterAfter(AuthenticationLoggingFilter(), RequestValidationFilter::class.java)
            .addFilterAt(staticKeyAuthenticationFilter, BasicAuthenticationFilter::class.java)
            .authorizeHttpRequests().anyRequest().permitAll()

        return http.build()
    }
}