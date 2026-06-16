package com.example.ssiach10ex4.repository

import com.example.ssiach10ex4.domain.Token
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.security.web.csrf.CsrfTokenRepository
import org.springframework.security.web.csrf.DefaultCsrfToken
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomCsrfTokenRepository(
    private val jpaTokenRepository: JpaTokenRepository,
) : CsrfTokenRepository {

    override fun generateToken(request: HttpServletRequest): CsrfToken {
        val uuid = UUID.randomUUID().toString()
        return DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", uuid)
    }

    override fun saveToken(token: CsrfToken, request: HttpServletRequest, response: HttpServletResponse) {
        val identifier = request.getHeader("X-IDENTIFIER")
        val existingToken = jpaTokenRepository.findByIdentifier(identifier)
        if (existingToken.isPresent) {
            existingToken.get().apply { this.token = token.token }
        } else {
            Token().apply {
                this.token = token.token
                this.identifier = identifier
            }.let { jpaTokenRepository.save(it) }
        }
    }

    override fun loadToken(request: HttpServletRequest): CsrfToken? {
        val identifier = request.getHeader("X-IDENTIFIER")
        val existingToken = jpaTokenRepository.findByIdentifier(identifier)
        if (existingToken.isPresent) {
            val token = existingToken.get()
            return DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token.token)
        }
        return null
    }
}