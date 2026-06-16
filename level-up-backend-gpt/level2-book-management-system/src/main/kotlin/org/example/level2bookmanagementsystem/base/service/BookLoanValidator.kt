package org.example.level2bookmanagementsystem.base.service

import org.example.level2bookmanagementsystem.base.repository.BookLoanRepository
import org.example.level2bookmanagementsystem.jpa.entity.Book
import org.example.level2bookmanagementsystem.jpa.entity.BookLoan
import org.example.level2bookmanagementsystem.security.entity.TbUser
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.example.level2bookmanagementsystem.swagger.exception.CustomBadRequestException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.ZonedDateTime

@Component
@Transactional(readOnly = true)
class BookLoanValidator(
    private val bookLoanRepository: BookLoanRepository,
) {

    fun validateLoanPreconditions(book: Book, user: TbUser) {
        val isOverdueBookLoan =
            bookLoanRepository.existsBookLoanByUserIdAndDueDateBeforeAndReturnDateIsNull(user.id!!, ZonedDateTime.now())
        if (isOverdueBookLoan) throw CustomBadRequestException(ErrorCode.EXISTING_OVERDUE_BOOK_LOAN)

        val userLoanCount = bookLoanRepository.countByUserIdAndReturnDateIsNull(user.id!!)
        if (userLoanCount >= BookLoan.MAX_LOANED_BOOK_COUNT) throw CustomBadRequestException(ErrorCode.EXCEEDING_NUMBER_OF_LOANS)

        val isDuplicateBookLoan =
            bookLoanRepository.existsBookLoanByBookIdAndUserIdAndReturnDateIsNull(book.id!!, user.id!!)
        if (isDuplicateBookLoan) throw CustomBadRequestException(ErrorCode.ALREADY_LOANED_BOOK)

        if (!book.isAvailabilityStock()) throw CustomBadRequestException(ErrorCode.OUT_OF_STOCK)
    }

    fun validateReturnPreconditions(loanUser: TbUser, returnUsername: String) {
        if (loanUser.userLoginId != returnUsername)
            throw CustomBadRequestException(ErrorCode.FORBIDDEN)
    }

}