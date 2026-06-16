package org.example.level2bookmanagementsystem.security.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.example.level2bookmanagementsystem.base.dto.response.UserResponse
import org.example.level2bookmanagementsystem.security.command.SignInCommand
import org.example.level2bookmanagementsystem.security.command.SignupCommand
import org.example.level2bookmanagementsystem.security.dto.AuthTokenResponse
import org.example.level2bookmanagementsystem.security.service.AuthService
import org.example.level2bookmanagementsystem.swagger.config.SwaggerApiErrorCodeExample
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.example.level2bookmanagementsystem.swagger.exception.CustomBadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "인증 API", description = "회원가입 및 인증 관련 API")
class AuthController(
    private val authService: AuthService,
) {

    @Operation(summary = "회원가입", description = "신규 회원을 등록합니다.")
    @SwaggerApiErrorCodeExample(value = [ErrorCode.INTERNAL_SERVER_ERROR, ErrorCode.INVALID_VALUE, ErrorCode.DUPLICATE_REQUEST])
    @PostMapping("/signup")
    fun signup(@RequestBody @Validated command: SignupCommand): ResponseEntity<UserResponse> {
        val result = authService.signup(command)
        return ResponseEntity.status(HttpStatus.CREATED).body(result)
    }


    @Operation(summary = "로그인", description = "아이디와 비밀번호로 로그인합니다.")
    @SwaggerApiErrorCodeExample(value = [ErrorCode.INVALID_VALUE, ErrorCode.NOT_FOUND_ENTITY, ErrorCode.INTERNAL_SERVER_ERROR])
    @PostMapping("/signin")
    fun signIn(@RequestBody @Validated command: SignInCommand, request: HttpServletRequest): ResponseEntity<AuthTokenResponse> {

        val token = authService.signIn(command, request.remoteAddr)
        return ResponseEntity.ok(token)
    }


    @Operation(summary = "토큰 재발급", description = "유효한 리프레쉬 토큰으로 신규 토큰을 발급합니다.")
    @SwaggerApiErrorCodeExample(value = [ErrorCode.UNAUTHORIZED, ErrorCode.AUTH_TOKEN_ERROR, ErrorCode.EXPIRED_TOKEN, ErrorCode.INTERNAL_SERVER_ERROR, ErrorCode.INVALID_AUTH_TOKEN, ErrorCode.NOT_EXISTS_AUTH_TOKEN])
    @PostMapping("/reissue")
    fun reissue(request: HttpServletRequest): ResponseEntity<AuthTokenResponse> {
        val header = request.getHeader("Authorization") ?: throw CustomBadRequestException(ErrorCode.NOT_EXISTS_AUTH_TOKEN)
        val prefix = "Bearer "
        if (!header.startsWith(prefix)) throw CustomBadRequestException(ErrorCode.NOT_EXISTS_AUTH_TOKEN)
        val refreshToken = header.removePrefix(prefix).trim()
        val tokens = authService.reissue(refreshToken, request.remoteAddr)
        return ResponseEntity.ok(tokens)
    }

    @PostMapping("/logout")
    @SwaggerApiErrorCodeExample(value = [ErrorCode.INTERNAL_SERVER_ERROR, ErrorCode.AUTH_TOKEN_ERROR, ErrorCode.NOT_FOUND_ENTITY])
    fun logout(principal: Principal): ResponseEntity<Any> {
        authService.logout(principal.name)
        return ResponseEntity.ok().build()
    }
}