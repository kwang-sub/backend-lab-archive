package org.example.level2bookmanagementsystem.base.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

@Schema(description = "사용자 요약 응답 ���체")
data class UserSimpleResponse(
    @Schema(description = "사용자 ID", example = "1")
    val id: UUID,
    @Schema(description = "사용자 아이디", example = "user123")
    val userId: String,
)
