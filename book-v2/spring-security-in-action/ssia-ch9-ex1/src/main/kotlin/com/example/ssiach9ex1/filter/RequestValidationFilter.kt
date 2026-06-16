package com.example.ssiach9ex1.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

class RequestValidationFilter : Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val httpServletRequest = request as HttpServletRequest
        val httpServletResponse = response as HttpServletResponse

        val requestId = httpServletRequest.getHeader("Request-id")
        if (requestId.isNullOrBlank()) {
            httpServletResponse.status = HttpServletResponse.SC_BAD_REQUEST
            return
        }

        chain.doFilter(request, response)
    }
}