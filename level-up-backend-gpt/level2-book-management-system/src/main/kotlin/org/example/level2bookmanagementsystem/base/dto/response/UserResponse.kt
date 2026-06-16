package org.example.level2bookmanagementsystem.base.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import org.example.level2bookmanagementsystem.security.entity.Role
import java.util.UUID

@Schema(description = "회원가입 응답 객체")
data class UserResponse(
    @Schema(description = "사용자 아이디", example = "user123")
    val id: UUID,
    @Schema(description = "사용자 아이디", example = "user123")
    val userLoginId: String,
    @Schema(description = "사용자 이름", example = "홍길동")
    val name: String,
    @Schema(description = "권한", example = "[ROLE_USER, ROLE_MANAGER, ROLE_ADMIN]")
    val role: Role,
)