package com.example.ssiach9ex1.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class StaticKeyAuthenticationFilter(
) : Filter {
    @field:Value("\${authorization.key}")
    private var authorizationKey: String? = null

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpServletRequest = request as HttpServletRequest
        val httpServletResponse = response as HttpServletResponse

        val authentication = httpServletRequest.getHeader("c")
        if (authentication == authorizationKey) chain.doFilter(request, response)
        else httpServletResponse.status = HttpServletResponse.SC_UNAUTHORIZED

    }
}