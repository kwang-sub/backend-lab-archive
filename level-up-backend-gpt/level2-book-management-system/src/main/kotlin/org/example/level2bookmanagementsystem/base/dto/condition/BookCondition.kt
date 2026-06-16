package org.example.level2bookmanagementsystem.base.dto.condition

import io.swagger.v3.oas.annotations.media.Schema

/**
 * 책 목록 검색 조건 DTO
 */
data class BookCondition(
    @Schema(description = "도서 제목", example = "")
    val title: String? = null,
    @Schema(description = "저자 이름", example = "")
    val author: String? = null,
    @Schema(description = "출판 연도", example = "")
    val publishedYear: Int? = null
)

