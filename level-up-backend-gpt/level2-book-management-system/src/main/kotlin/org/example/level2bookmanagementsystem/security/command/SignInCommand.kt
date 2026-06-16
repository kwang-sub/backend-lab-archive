package org.example.level2bookmanagementsystem.security.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(description = "로그인 요청 객체")
data class SignInCommand(
    @field:NotBlank
    @Schema(description = "사용자 아이디", example = "user123")
    val userLoginId: String,

    @field:NotBlank
    @Schema(description = "비밀번호", example = "password123")
    val password: String
) {
}