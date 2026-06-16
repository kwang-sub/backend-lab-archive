package org.example.ssiach09ex1.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse


class RequestValidationFilter: Filter {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val httpResponse = response as HttpServletResponse

        val requestId = httpRequest.getHeader("Request-Id")
        if (requestId.isNullOrBlank()) {
            httpResponse.status = HttpServletResponse.SC_BAD_REQUEST
            return
        }
        chain.doFilter(request, httpResponse)
    }
}