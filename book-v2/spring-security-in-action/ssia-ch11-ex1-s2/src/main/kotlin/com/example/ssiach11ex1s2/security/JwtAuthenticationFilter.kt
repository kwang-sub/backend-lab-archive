package com.example.ssiach11ex1s2.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets

class JwtAuthenticationFilter : OncePerRequestFilter() {

    @Value("\${jwt.signing.key}")
    private var signingKey: String = "null"

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getHeader("Authorization")

        val key = Keys.hmacShaKeyFor(signingKey.toByteArray(StandardCharsets.UTF_8))

        val claims = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jwt)
            .body
        val username = claims["user"].toString()
        val authority = SimpleGrantedAuthority("user")
        val auth = UsernamePasswordAuthentication(username, null, listOf(authority))

        SecurityContextHolder.getContext()
            .authentication = auth

        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.servletPath == "/login"
    }
}
