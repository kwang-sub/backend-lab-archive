package org.example.ssiach09ex1.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class StaticKeyAuthenticationFilter: Filter {

    @Value("\${authorization.key}")
    private lateinit var authorizationKey: String

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpServletRequest = request as HttpServletRequest
        val httpServletResponse = response as HttpServletResponse
        val authentication = httpServletRequest.getHeader("Authorization")
        if (authorizationKey == authentication) {
            chain.doFilter(request, response)
        } else {
            httpServletResponse.status = HttpServletResponse.SC_UNAUTHORIZED
        }
    }
}