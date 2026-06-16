package com.example.ssiach10ex1.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.web.csrf.CsrfToken

class CsrfTokenLogger : Filter {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val token = request.getAttribute("_csrf") as? CsrfToken
        log.debug("csrf token ${token?.token}")
        chain.doFilter(request, response)
    }
}