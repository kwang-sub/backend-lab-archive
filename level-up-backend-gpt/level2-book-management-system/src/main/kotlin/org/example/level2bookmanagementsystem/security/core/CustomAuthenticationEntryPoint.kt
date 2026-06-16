package org.example.level2bookmanagementsystem.security.core

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.example.level2bookmanagementsystem.base.dto.response.ErrorResponse
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.ZonedDateTime

@Component
class CustomAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {

    private val log = KotlinLogging.logger {}

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        log.debug("Authentication failure. Request Method: {}", request.method)

        response.status = HttpServletResponse.SC_UNAUTHORIZED // 401
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = StandardCharsets.UTF_8.name()
        val responseDto = ErrorResponse(
            code = ErrorCode.UNAUTHORIZED.code,
            message = ErrorCode.UNAUTHORIZED.message,
            status = ErrorCode.UNAUTHORIZED.httpCode.value(),
            path = "",
            timestamp = ZonedDateTime.now()
        )
        response.writer.write(objectMapper.writeValueAsString(responseDto))
    }
}