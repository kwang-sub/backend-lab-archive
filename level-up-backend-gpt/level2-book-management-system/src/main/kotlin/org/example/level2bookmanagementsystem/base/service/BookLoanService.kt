package org.example.level2bookmanagementsystem.base.service

import mu.KotlinLogging
import org.example.level2bookmanagementsystem.base.dto.response.BookLoanResponse
import org.example.level2bookmanagementsystem.base.mapper.BookLoanMapper
import org.example.level2bookmanagementsystem.base.repository.BookLoanRepository
import org.example.level2bookmanagementsystem.base.repository.UserRepository
import org.example.level2bookmanagementsystem.jpa.entity.Book
import org.example.level2bookmanagementsystem.jpa.entity.BookLoan
import org.example.level2bookmanagementsystem.jpa.repository.BookRepository
import org.example.level2bookmanagementsystem.security.entity.TbUser
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.example.level2bookmanagementsystem.swagger.exception.CustomBadRequestException
import org.example.level2bookmanagementsystem.swagger.exception.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookLoanService(
    private val bookLoanRepository: BookLoanRepository,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val bookLoanMapper: BookLoanMapper,
    private val bookLoanValidator: BookLoanValidator,
    private val bookService: BookService,
) {
    private val log = KotlinLogging.logger {}

    fun loanBook(bookId: Long, userLoginId: String, isRenew: Boolean): BookLoanResponse {
        val book: Book = bookRepository.findByIdAndPessimisticLock(bookId)
            .orElseThrow { EntityNotFoundException(Book::class) }
        val user = userRepository.findByUserLoginId(userLoginId)
            ?.also {
                if (it.isLoanAvailable().not())
                    throw CustomBadRequestException(ErrorCode.EXISTING_LOAN_RESTRICTION)
            }
            ?: throw EntityNotFoundException(TbUser::class)

        bookLoanValidator.validateLoanPreconditions(book, user)

        return BookLoan.loan(book, user, isRenew).also {
            bookLoanRepository.save(it)
            bookService.decreaseStock(bookId)
            log.info("Book loaned: bookId=$bookId to userLoginId=$userLoginId")
        }.let(bookLoanMapper::toResponse)
    }

    fun returnBook(id: Long, returnUsername: String): BookLoanResponse {
        val bookLoan: BookLoan = bookLoanRepository.findById(id)
            .orElseThrow { throw EntityNotFoundException(BookLoan::class) }
        val loanUser = bookLoan.user

        bookLoanValidator.validateReturnPreconditions(loanUser, returnUsername)

        return bookLoan.returned().also {
            bookLoanRepository.save(it)
            loanUser.postponeLoanAvailableDates(it.calculateLateReturnDays())
                .let { userRepository.save(loanUser) }
            bookService.increaseStock(bookLoan.book.id!!)
            log.info("Book returned: bookLoanId=$id by userLoginId=$returnUsername")
        }.let(bookLoanMapper::toResponse)
    }
}