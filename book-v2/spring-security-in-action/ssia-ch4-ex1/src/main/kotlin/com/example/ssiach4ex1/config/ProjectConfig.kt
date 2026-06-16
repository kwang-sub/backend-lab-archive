package com.example.ssiach4ex1.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.DelegatingPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder

@Configuration
class ProjectConfig {

    @Bean
    fun passwordEncoder(): PasswordEncoder {

        val encoders = hashMapOf<String, PasswordEncoder>()
        encoders["noop"] = NoOpPasswordEncoder.getInstance()
        encoders["bcrypt"] = BCryptPasswordEncoder()
        encoders["scrypt"] = SCryptPasswordEncoder(2,2,2,2,2)
        return DelegatingPasswordEncoder("bcrypt", encoders)
    }
}