package org.example.level2bookmanagementsystem.security.core

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.level2bookmanagementsystem.base.dto.response.ErrorResponse
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class CustomAccessDeniedHandler(
    private val objectMapper: ObjectMapper
) : AccessDeniedHandler {

    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException?
    ) {
        response.status = HttpServletResponse.SC_FORBIDDEN
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        val responseDto = ErrorResponse(
            code = ErrorCode.FORBIDDEN.code,
            message = ErrorCode.FORBIDDEN.message,
            status = ErrorCode.FORBIDDEN.httpCode.value(),
            path = "",
            timestamp = ZonedDateTime.now()
        )
        response.writer.write(objectMapper.writeValueAsString(responseDto))
    }
}