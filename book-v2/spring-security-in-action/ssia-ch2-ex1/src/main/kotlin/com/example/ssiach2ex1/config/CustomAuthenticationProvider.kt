package com.example.ssiach2ex1.config

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication

class CustomAuthenticationProvider : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password: String = authentication.credentials.toString()

        if ("john" == username && "12345" == password) return UsernamePasswordAuthenticationToken(
            username,
            password,
            listOf()
        )

        throw AuthenticationCredentialsNotFoundException("Error in authentication!")
    }

    override fun supports(authentication: Class<*>): Boolean =
        UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
}