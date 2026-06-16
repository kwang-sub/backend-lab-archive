package org.example.level2bookmanagementsystem.base.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import java.time.ZonedDateTime

@Schema(description = "에러 응답 객체")
data class ErrorResponse(
    @Schema(description = "에러 코드", example = "ERR001")
    val code: String,
    @Schema(description = "에러 메시지", example = "잘못된 요청입니다.")
    val message: String,
    @Schema(description = "HTTP 상태 코드", example = "400")
    val status: Int,
    @Schema(description = "요청 경로", example = "/api/v1/book")
    val path: String,
    @Schema(description = "에러 발생 시각", example = "2023-01-01T10:00:00+09:00")
    val timestamp: ZonedDateTime,
    @Schema(description = "필드 에러 목록")
    var fieldErrors: List<ErrorDetailResponse> = emptyList(),
)
