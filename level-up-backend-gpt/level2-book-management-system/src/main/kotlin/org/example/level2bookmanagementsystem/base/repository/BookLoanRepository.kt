package org.example.level2bookmanagementsystem.base.repository

import org.example.level2bookmanagementsystem.jpa.entity.BookLoan
import org.springframework.data.jpa.repository.JpaRepository
import java.time.ZonedDateTime
import java.util.UUID

interface BookLoanRepository: JpaRepository<BookLoan, Long> {

    fun existsBookLoanByBookIdAndUserIdAndReturnDateIsNull(bookId: Long, userId: UUID): Boolean
    fun countByUserIdAndReturnDateIsNull(userId: UUID): Long
    fun existsBookLoanByUserIdAndDueDateBeforeAndReturnDateIsNull(userId: UUID, dueDate: ZonedDateTime): Boolean
    fun countByBookId(bookId: Long): Long
}