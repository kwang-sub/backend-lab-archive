package com.example.ssiach11ex1s2.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication

class OtpAuthenticationProvider(
    private val proxy: AuthenticationServerProxy
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val username: String = authentication.name
        val code: String = authentication.credentials.toString()
        val result = proxy.sendOTP(username, code)
        if (result) return OtpAuthentication(username, code) else throw BadCredentialsException("Bad credentials.")

    }

    override fun supports(authentication: Class<*>): Boolean =
        OtpAuthentication::class.java.isAssignableFrom(authentication)
}