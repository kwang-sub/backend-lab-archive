package org.example.ssiach5.config

import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableAsync
class SecurityConfig {

    @Bean
    fun initializingBean(): InitializingBean {
        return InitializingBean { SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_GLOBAL) }
    }

    @Bean
    fun customAuthenticationProvider(): CustomAuthenticationProvider {
        return CustomAuthenticationProvider(userDetailsService(), passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return NoOpPasswordEncoder.getInstance()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User.builder()
            .username("kwang")
            .password("1234")
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(user)
    }

    @Bean
    fun securityFilterChane(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic { c ->
            c.realmName("OTHER")
            c.authenticationEntryPoint(CustomEntryPoint())
        }
        http.authenticationProvider(customAuthenticationProvider())
        http.authorizeRequests().anyRequest().authenticated()
        return http.build()
    }
}