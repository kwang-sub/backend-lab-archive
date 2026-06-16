package org.example.level2bookmanagementsystem.base.dto.response

import org.example.level2bookmanagementsystem.jpa.status.BookLoanStatus
import java.time.ZonedDateTime

data class BookLoanResponse(
    val id: Long,
    val status: BookLoanStatus,
    val book: BookResponse,
    val user: UserResponse,
    val loanDate: ZonedDateTime,
    val returnDate: ZonedDateTime?,
    val dueDate: ZonedDateTime,
    val renewCount: Int,
)