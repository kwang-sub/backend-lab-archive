package org.example.level2bookmanagementsystem.swagger.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.example.level2bookmanagementsystem.base.dto.command.BookCommand
import org.example.level2bookmanagementsystem.base.dto.condition.BookCondition
import org.example.level2bookmanagementsystem.base.dto.response.BookResponse
import org.example.level2bookmanagementsystem.base.dto.response.BookSimpleResponse
import org.example.level2bookmanagementsystem.base.service.BookService
import org.example.level2bookmanagementsystem.swagger.config.SwaggerApiErrorCodeExample
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Tag(name = "Book API", description = "도서 관련 API")
@RestController
@RequestMapping("/api/v1/books")
class BookController(
    private val bookService: BookService
) {

    @Operation(summary = "도서 단건 조회", description = "ID로 도서 정보를 조회합니다.")
    @SwaggerApiErrorCodeExample(value = [ErrorCode.NOT_FOUND_ENTITY, ErrorCode.INTERNAL_SERVER_ERROR])
    @GetMapping("/{id}")
    fun getBook(@PathVariable id: Long): ResponseEntity<BookResponse> {
        val result = bookService.getBook(id)
        return ResponseEntity.ok(result)
    }

    @Operation(summary = "도서 목록 조회", description = "페이징 및 조건으로 도서 목록을 조회합니다.")
    @SwaggerApiErrorCodeExample(value = [ErrorCode.INTERNAL_SERVER_ERROR])
    @GetMapping
    fun getAll(
        @ParameterObject
        @PageableDefault(size = 10, page = 0, sort = ["id,desc"])
        pageable: Pageable,
        condition: BookCondition
    ): ResponseEntity<Page<BookSimpleResponse>> {
        val result = bookService.getAll(pageable, condition)
        return ResponseEntity.ok(result)
    }

    @Operation(summary = "도서 등록", description = "새로운 도서를 등록합니다.")
    @SwaggerApiErrorCodeExample(value = [ErrorCode.INVALID_VALUE, ErrorCode.INTERNAL_SERVER_ERROR])
    @PostMapping
    fun create(@RequestBody @Validated command: BookCommand): ResponseEntity<BookResponse> {
        val result = bookService.create(command)
        return ResponseEntity.ok(result)
    }

    @Operation(summary = "도서 정보 수정", description = "도서 정보를 수정합니다.")
    @SwaggerApiErrorCodeExample(value = [ErrorCode.NOT_FOUND_ENTITY, ErrorCode.INVALID_VALUE, ErrorCode.INTERNAL_SERVER_ERROR])
    @PatchMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody @Validated command: BookCommand
    ): ResponseEntity<BookResponse> {
        val result = bookService.update(id, command)
        return ResponseEntity.ok(result)
    }

    @Operation(summary = "도서 삭제", description = "ID로 도서를 삭제합니다.")
    @SwaggerApiErrorCodeExample(value = [ErrorCode.NOT_FOUND_ENTITY, ErrorCode.INTERNAL_SERVER_ERROR])
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        bookService.delete(id)
        return ResponseEntity.noContent().build()
    }
}