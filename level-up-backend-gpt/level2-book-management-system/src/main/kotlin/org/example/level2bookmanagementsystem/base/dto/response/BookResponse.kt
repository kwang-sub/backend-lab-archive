package org.example.level2bookmanagementsystem.base.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.ZonedDateTime

@Schema(description = "도서 상세 응답 객체")
data class BookResponse(
    @Schema(description = "도서 ID", example = "1")
    val id: Long,
    @Schema(description = "도서 제목", example = "자바의 정석")
    val title: String,
    @Schema(description = "저자", example = "남궁성")
    val author: String,
    @Schema(description = "출판사", example = "도우출판")
    val publisher: String,
    @Schema(description = "출판일", example = "2023-01-01")
    val publishedDate: LocalDate,
    @Schema(description = "ISBN", example = "9788994492032")
    val isbn: String,
    @Schema(description = "가격", example = "35000")
    val price: Int,
    @Schema(description = "등록일시", example = "2023-01-01T10:00:00+09:00")
    val createdAt: ZonedDateTime? = null,
    @Schema(description = "수정일시", example = "2023-01-02T10:00:00+09:00")
    val updatedAt: ZonedDateTime,
)