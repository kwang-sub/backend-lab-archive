package com.example.ssiach11ex1s2.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets

class InitialAuthenticationFilter(
    private val manager: AuthenticationManager,
) : OncePerRequestFilter() {
    @Value("\${jwt.signing.key}")
    private var signingKey: String = "null"

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val username = request.getHeader("username") ?: { response.status = HttpServletResponse.SC_BAD_REQUEST }
        val password = request.getHeader("password") ?: { response.status = HttpServletResponse.SC_BAD_REQUEST }
        val code: String? = request.getHeader("code")

        if (code == null) {
            val authentication = UsernamePasswordAuthentication(username, password)
            manager.authenticate(authentication)
        } else {
            val authentication = OtpAuthentication(username, code)
            manager.authenticate(authentication)
            val key = Keys.hmacShaKeyFor(signingKey.toByteArray(StandardCharsets.UTF_8))

            val jwt = Jwts.builder()
                .setClaims(mapOf("username" to username))
                .signWith(key)
                .compact()
            response.setHeader("Authorization", jwt)
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.servletPath != "/login"
    }
}