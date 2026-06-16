package org.example.level2bookmanagementsystem.security.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import org.example.level2bookmanagementsystem.security.entity.Role

@Schema(description = "회원가입 요청 객체")
data class SignupCommand(
    @Schema(description = "사용자 아이디", example = "user123")
    @get:NotBlank
    val userLoginId: String,
    @Schema(description = "사용자 이름", example = "홍길동")
    @get:NotBlank
    val name: String,
    @Schema(description = "비밀번호", example = "password123")
    @get:NotBlank
    val password: String,
    @Schema(description = "권한", example = "[ROLE_USER, ROLE_MANAGER, ROLE_ADMIN]")
    val role: Role,
)