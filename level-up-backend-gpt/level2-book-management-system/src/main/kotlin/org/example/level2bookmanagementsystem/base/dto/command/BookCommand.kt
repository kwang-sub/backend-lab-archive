package org.example.level2bookmanagementsystem.base.dto.command

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class BookCommand(

    @param:Schema(description = "책 제목", example = "코틀린 프로그래밍")
    @get:NotBlank
    val title: String,

    @param:Schema(description = "저자 이름", example = "홍길동")
    @get:NotBlank
    val author: String,

    @param:Schema(description = "출판사", example = "한빛미디어")
    @get:NotBlank
    val publisher: String,

    @param:Schema(description = "출판일", example = "2023-01-15")
    @get:NotNull
    val publishedDate: LocalDate,

    @param:Schema(description = "ISBN 번호", example = "978-3-16-148410-0")
    @get:NotBlank
    val isbn: String,

    @param:Schema(description = "가격", example = "15000")
    @get:Min(value = 1_000)
    val price: BigDecimal,

    @param:Schema(description = "재고 수량", example = "100")
    val stockQuantity: Int = 0,
)