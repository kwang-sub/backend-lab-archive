package org.example.level2bookmanagementsystem.security.core

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.example.level2bookmanagementsystem.base.dto.response.ErrorResponse
import org.example.level2bookmanagementsystem.security.util.JwtUtil
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.server.PathContainer
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.pattern.PathPatternParser
import java.time.ZonedDateTime


@Component
class JwtAuthenticationFilter(
    private val jwtUtil: JwtUtil,
    private val customUserDetailsService: CustomUserDetailsService,
    private val objectMapper: ObjectMapper,
) : OncePerRequestFilter() { // OncePerRequestFilter를 상속받아 요청당 한 번만 실행되는 필터를 구현합니다.

    private val parser = PathPatternParser()
    private val excludeMatchers = listOf(
        "POST" to parser.parse("/error"),
        "POST" to parser.parse("/api/v1/auth/signup"),
        "POST" to parser.parse("/api/v1/auth/signin"),
        "POST" to parser.parse("/api/v1/auth/reissue"),
        "GET" to parser.parse("/v3/api-docs/**"),
        "GET" to parser.parse("/swagger-ui/**"),
        "GET" to parser.parse("/swagger-resources/**"),
    )

    // 필터가 적용되지 않아야 하는 요청을 정의합니다.
    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val requestMethod = request.method
        val requestPath = request.requestURI

        return excludeMatchers.any { (expectedMethod, expectedPathPattern) ->
            expectedMethod == requestMethod && expectedPathPattern.matches(PathContainer.parsePath(requestPath))
        }
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // 이미 인증 정보가 있는 경우, 필터 체인을 계속 진행
        if (SecurityContextHolder.getContext().authentication != null) {
            filterChain.doFilter(request, response)
            return
        }

        // Authorization 헤더에서 JWT 토큰을 추출합니다.
        // Bearer 토큰 형식을 사용한다고 가정합니다.
        // 예: Authorization: Bearer <token>
        // 토큰이 없거나 형식이 잘못된 경우, 401 Unauthorized 응답을 반환합니다.
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            respondWithUnauthorizedError(response)
            return
        }

        val token = authHeader.substring(7)
        try {
            val username = jwtUtil.extractSubject(token)
            val userDetails: UserDetails = customUserDetailsService.loadUserByUsername(username)

            if (jwtUtil.isValidateToken(token, userDetails.username, JwtUtil.Companion.TokenType.ACCESS)) {
                val authentication = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.authorities
                )
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            respondWithUnauthorizedError(response)
            return
        }

        filterChain.doFilter(request, response)
    }

    private fun respondWithUnauthorizedError(response: HttpServletResponse) {
        val responseDto = ErrorResponse(
            code = ErrorCode.UNAUTHORIZED.code,
            message = ErrorCode.UNAUTHORIZED.message,
            status = ErrorCode.UNAUTHORIZED.httpCode.value(),
            path = "",
            timestamp = ZonedDateTime.now()
        )

        response.apply {
            this.status = HttpStatus.UNAUTHORIZED.value()
            this.contentType = MediaType.APPLICATION_JSON_VALUE
            this.writer.write(objectMapper.writeValueAsString(responseDto))
        }.flushBuffer()
    }
}