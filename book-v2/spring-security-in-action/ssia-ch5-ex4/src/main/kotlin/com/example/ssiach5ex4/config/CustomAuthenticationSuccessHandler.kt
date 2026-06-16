package com.example.ssiach5ex4.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationSuccessHandler: AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        var authorities = authentication?.authorities
        var auth = authorities?.firstOrNull { a -> a.authority == "read" }
        when (auth) {
            null -> response?.sendRedirect("/error")
            else -> response?.sendRedirect("/home")
        }
    }
}