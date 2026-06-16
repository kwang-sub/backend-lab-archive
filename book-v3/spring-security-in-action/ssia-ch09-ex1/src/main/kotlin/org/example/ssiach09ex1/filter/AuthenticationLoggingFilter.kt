package org.example.ssiach09ex1.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import java.util.logging.Logger

class AuthenticationLoggingFilter: Filter {

    private val logger: Logger = Logger.getLogger(AuthenticationLoggingFilter::class.java.name)

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpServletRequest = request as HttpServletRequest
        val requestId = httpServletRequest.getHeader("Request-Id")
        logger.info("Request Id: $requestId")

        chain!!.doFilter(request, response)
    }
}