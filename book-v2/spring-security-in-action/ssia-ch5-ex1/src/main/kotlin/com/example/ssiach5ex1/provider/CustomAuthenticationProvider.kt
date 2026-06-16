package com.example.ssiach5ex1.provider

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

class CustomAuthenticationProvider(
    private val passwordEncoder: PasswordEncoder,
    private val userDetailsService: UserDetailsService
) : AuthenticationProvider {


    override fun authenticate(authentication: Authentication): Authentication {

        val username = authentication.name
        val password = authentication.credentials.toString()

        val userDetails = userDetailsService.loadUserByUsername(username)
        if (passwordEncoder.matches(password, userDetails.password)) return UsernamePasswordAuthenticationToken(
            username, password, userDetails.authorities
        )
        throw BadCredentialsException("Something went wrong!")
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class
    }
}