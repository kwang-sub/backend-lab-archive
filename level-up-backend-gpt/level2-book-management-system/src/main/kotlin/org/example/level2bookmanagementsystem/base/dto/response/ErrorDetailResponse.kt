package org.example.level2bookmanagementsystem.base.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "에러 상세 정보 응답")
data class ErrorDetailResponse(
    @Schema(description = "에러가 발생한 필드명", example = "username")
    val field: String,
    @Schema(description = "에러 메시지", example = "필수 입력값입니다.")
    val message: String,
)