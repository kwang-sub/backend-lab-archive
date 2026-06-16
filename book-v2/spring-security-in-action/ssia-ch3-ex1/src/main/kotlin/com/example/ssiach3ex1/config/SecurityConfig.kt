package com.example.ssiach3ex1.config

import com.example.ssiach3ex1.SimpleUser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {


    @Bean
    fun securityFilterChane(http: HttpSecurity): SecurityFilterChain {

        val userDetails = User.withUsername("bill")
            .password("12345")
            .authorities("read", "write")
            .accountExpired(false)
            .disabled(true)
            .build()

        val builder: User.UserBuilder = User.withUsername("bill")
        val u1 = builder.password("12345")
            .authorities("read")
            .passwordEncoder({ p -> p })
            .accountExpired(false)
            .disabled(true)
            .build()


        val inMemoryUserDetailsManager = InMemoryUserDetailsManager()
//        inMemoryUserDetailsManager.createUser(userDetails)
//        inMemoryUserDetailsManager.createUser(DummyUser())
        inMemoryUserDetailsManager.createUser(SimpleUser("kwnag", "12345"))
        http.userDetailsService(inMemoryUserDetailsManager)

        http.authorizeHttpRequests().anyRequest().authenticated()
        return http.build()
    }
}
