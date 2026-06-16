package org.example.level2bookmanagementsystem.security.config

import org.example.level2bookmanagementsystem.security.core.CustomAccessDeniedHandler
import org.example.level2bookmanagementsystem.security.core.CustomAuthenticationEntryPoint
import org.example.level2bookmanagementsystem.security.core.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableMethodSecurity
class SecurityConfig(
    private val customAuthenticationEntryPoint: CustomAuthenticationEntryPoint,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter, // JWT 인증 필터
    private val customAccessDeniedHandler: CustomAccessDeniedHandler,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(
                        "/api/v1/auth/signup",
                        "/api/v1/auth/signin",
                        "/api/v1/auth/reissue",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/error"
                    ).permitAll()
                    .requestMatchers(HttpMethod.POST, "api/v1/book-loans").hasRole("USER")
                    .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .exceptionHandling {
                it.authenticationEntryPoint(customAuthenticationEntryPoint) // 인증 실패 시 처리
                it.accessDeniedHandler(customAccessDeniedHandler) // 인가 실패 시 처리
            }
//        UsernamePasswordAuthenticationFilter는 Spring Security에서 로그인 처리를 담당하는 필터
//        JWT 기반 인증을 사용하는 경우 이 필터 전에 JWT 필터가 있어야 함
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .httpBasic { it.disable() }

        return http.build()
    }



    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(http: HttpSecurity): AuthenticationManager {
        return http.getSharedObject(AuthenticationManagerBuilder::class.java).build()
    }
}