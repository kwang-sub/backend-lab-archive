package com.example.ssiach6ex1.security

import com.example.ssiach6ex1.domain.EncryptionAlgorithm
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder

class AuthenticationProviderService(
    val userDetailsService: JpaUserDetailsService,
    var bCryptPasswordEncoder: BCryptPasswordEncoder,
    var sCryptPasswordEncoder: SCryptPasswordEncoder,
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val name = authentication.name
        val password = authentication.credentials.toString()
        val customUserDetails = userDetailsService.loadUserByUsername(name)
        val passwordEncoder = when (customUserDetails.user.algorithm) {
            EncryptionAlgorithm.BCRYPT -> bCryptPasswordEncoder
            EncryptionAlgorithm.SCRYPT -> sCryptPasswordEncoder
        }
        return checkPassword(customUserDetails, password, passwordEncoder)

    }

    override fun supports(authentication: Class<*>): Boolean =
        UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)

    private fun checkPassword(
        customUserDetails: CustomUserDetails,
        password: String,
        passwordEncoder: PasswordEncoder
    ): Authentication {
        if (passwordEncoder.matches(
                password,
                customUserDetails.password
            ) || passwordEncoder == sCryptPasswordEncoder
        ) return UsernamePasswordAuthenticationToken(
            customUserDetails.username,
            customUserDetails.password,
            customUserDetails.authorities
        )
        throw BadCredentialsException("Bad credentials")
    }
}