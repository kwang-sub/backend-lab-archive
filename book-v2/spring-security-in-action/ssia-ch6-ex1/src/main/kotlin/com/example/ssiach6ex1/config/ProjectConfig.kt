package com.example.ssiach6ex1.config

import com.example.ssiach6ex1.security.AuthenticationProviderService
import com.example.ssiach6ex1.security.JpaUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class ProjectConfig(
    private val jpaUserDetailsService: JpaUserDetailsService,
) {

    @Bean
    fun authenticationProviderService(): AuthenticationProviderService {
        return AuthenticationProviderService(jpaUserDetailsService, bCryptPasswordEncoder(), sCryptPasswordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun sCryptPasswordEncoder(): SCryptPasswordEncoder = SCryptPasswordEncoder(2,2,2,2,2)

    @Bean
    fun securityFilterChane(http: HttpSecurity): SecurityFilterChain {
        http.formLogin()
            .defaultSuccessUrl("/main", true)
        http.userDetailsService(jpaUserDetailsService)
        http.authenticationProvider(authenticationProviderService())
        http
            .authorizeHttpRequests().requestMatchers("/insert").permitAll()
            .and()
            .authorizeHttpRequests().anyRequest().authenticated()

        return http.build()
    }
}