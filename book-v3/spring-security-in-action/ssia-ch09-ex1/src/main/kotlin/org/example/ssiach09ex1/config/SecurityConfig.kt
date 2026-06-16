package org.example.ssiach09ex1.config

import org.example.ssiach09ex1.filter.AuthenticationLoggingFilter
import org.example.ssiach09ex1.filter.RequestValidationFilter
import org.example.ssiach09ex1.filter.StaticKeyAuthenticationFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
class SecurityConfig {

    @Autowired
    private lateinit var staticKeyAuthenticationFilter: StaticKeyAuthenticationFilter

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic()

        http
            .addFilterAt(staticKeyAuthenticationFilter, BasicAuthenticationFilter::class.java)
//            .addFilterBefore(RequestValidationFilter(), BasicAuthenticationFilter::class.java)
//            .addFilterAfter(AuthenticationLoggingFilter(), BasicAuthenticationFilter::class.java)
            .authorizeHttpRequests { auth -> auth.anyRequest().permitAll() }

        return http.build()
    }
}