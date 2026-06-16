package com.example.ssiach11ex1s2.config

import com.example.ssiach11ex1s2.security.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.web.client.RestTemplate

@Configuration
class ProjectConfig(

) {

    @Bean
    fun usernamePasswordAuthenticationProvider(): UsernamePasswordAuthenticationProvider = UsernamePasswordAuthenticationProvider(authenticationServerProxy())
    @Bean
    fun jwtAuthenticationFilter(): JwtAuthenticationFilter = JwtAuthenticationFilter()
    @Bean
    fun otpAuthenticationProvider(): OtpAuthenticationProvider = OtpAuthenticationProvider(authenticationServerProxy())

    @Bean
    fun authenticationServerProxy(): AuthenticationServerProxy = AuthenticationServerProxy(restTemplate())

    @Bean
    fun initialAuthenticationFilter(): InitialAuthenticationFilter {
        return InitialAuthenticationFilter(authenticationManager2())
    }

    @Bean
    fun restTemplate(): RestTemplate = RestTemplate()


    @Bean
    fun authenticationManager2(): AuthenticationManager = ProviderManager(otpAuthenticationProvider(), usernamePasswordAuthenticationProvider())

//    @Bean
//    fun configure(auth: AuthenticationManagerBuilder) {
//        auth.authenticationProvider(otpAuthenticationProvider())
//            .authenticationProvider(usernamePasswordAuthenticationProvider())
//    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()
        http.addFilterAt(initialAuthenticationFilter(), BasicAuthenticationFilter::class.java)
            .addFilterAt(jwtAuthenticationFilter(), BasicAuthenticationFilter::class.java)

        http.authorizeHttpRequests().anyRequest().authenticated()
        return http.build()
    }
}