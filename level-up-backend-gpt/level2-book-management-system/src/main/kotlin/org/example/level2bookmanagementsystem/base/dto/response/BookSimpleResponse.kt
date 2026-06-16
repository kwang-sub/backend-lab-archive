package org.example.level2bookmanagementsystem.base.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import java.time.ZonedDateTime

@Schema(description = "도서 요약 응답 객체")
data class BookSimpleResponse(
    @Schema(description = "도서 ID", example = "1")
    val id: Long,
    @Schema(description = "도서 제목", example = "자바의 정석")
    val title: String,
    @Schema(description = "가격", example = "35000")
    val price: Int,
    @Schema(description = "등록일시", example = "2023-01-01T10:00:00+09:00")
    val createdAt: ZonedDateTime? = null,
    @Schema(description = "수정일시", example = "2023-01-02T10:00:00+09:00")
    val updatedAt: ZonedDateTime,
)