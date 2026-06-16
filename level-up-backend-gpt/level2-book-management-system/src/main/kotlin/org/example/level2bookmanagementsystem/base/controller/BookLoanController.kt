package org.example.level2bookmanagementsystem.base.controller

import mu.KotlinLogging
import org.example.level2bookmanagementsystem.base.dto.command.BookLoanCommand
import org.example.level2bookmanagementsystem.base.dto.response.BookLoanResponse
import org.example.level2bookmanagementsystem.base.service.BookLoanService
import org.example.level2bookmanagementsystem.security.core.CustomUserDetails
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/book-loans")
class BookLoanController(
    private val bookLoanService: BookLoanService
) {
    private val log = KotlinLogging.logger {}

    @PostMapping
    fun loanBook(
        @RequestBody @Validated command: BookLoanCommand,
        @AuthenticationPrincipal user: CustomUserDetails
    ): BookLoanResponse {
        return bookLoanService.loanBook(command.bookId, user.username, command.isRenew)
    }

    @PostMapping("/{id}/return")
    fun returnBook(
        @PathVariable id: Long,
        @AuthenticationPrincipal user: CustomUserDetails
    ): BookLoanResponse {
        return bookLoanService.returnBook(id, user.username)
    }
}