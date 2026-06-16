package org.example.level2bookmanagementsystem.util

import jakarta.transaction.Transactional
import org.example.level2bookmanagementsystem.base.repository.BookLoanRepository
import org.example.level2bookmanagementsystem.base.repository.UserRepository
import org.example.level2bookmanagementsystem.base.repository.UserTokenRepository
import org.example.level2bookmanagementsystem.jpa.entity.Book
import org.example.level2bookmanagementsystem.jpa.entity.BookLoan
import org.example.level2bookmanagementsystem.jpa.repository.BookRepository
import org.example.level2bookmanagementsystem.security.dto.AuthTokenResponse
import org.example.level2bookmanagementsystem.security.entity.LoanInfo
import org.example.level2bookmanagementsystem.security.entity.Role
import org.example.level2bookmanagementsystem.security.entity.TbUser
import org.example.level2bookmanagementsystem.security.entity.UserToken
import org.example.level2bookmanagementsystem.security.util.JwtUtil
import org.example.level2bookmanagementsystem.swagger.constant.ErrorCode
import org.example.level2bookmanagementsystem.swagger.exception.CustomBadRequestException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDate
import java.time.ZonedDateTime

@Component
@Transactional
class EntityFactory(
    private val userRepository: UserRepository,
    private val userTokenRepository: UserTokenRepository,
    private val bookRepository: BookRepository,
    private val jwtUtil: JwtUtil,
    private val bookLoanRepository: BookLoanRepository
) {

    fun createBook(stock: Int = 10): Book {
        return Book(
            title = "Test Book",
            author = "Test Author",
            publisher = "1234567890",
            publishedDate = LocalDate.now(),
            isbn = "978-3-16-148410-0",
            price = BigDecimal.valueOf(10000),
            stockQuantity = stock
        ).let { book -> bookRepository.save(book) }
    }

    fun createBooks(amount: Long): List<Book> {
        val publishedDate = LocalDate.now()
        return (1..amount).map {
            Book(
                title = "Test Book $it",
                author = "Test Author $it",
                publisher = "Test Publisher $it",
                publishedDate = publishedDate,
                isbn = "978-3-16-148410-$it",
                price = BigDecimal.valueOf(10000) + BigDecimal.valueOf(it)
            )
        }.let { books -> bookRepository.saveAll(books) }
    }

    fun createUser(userId: String, password: String, passwordEncoder: PasswordEncoder, loanInfo: LoanInfo = LoanInfo(), role: Role = Role.ROLE_USER): TbUser {
        return TbUser(
            userLoginId = userId,
            name = "Test User",
            password = passwordEncoder.encode(password),
            role = role,
            loanInfo
        ).let { user -> userRepository.save(user) }
    }

    fun createToken(
        userLoginId: String,
        refreshToken: String,
        expiresAt: ZonedDateTime = LocalDate.now().plusDays(1).atStartOfDay().atZone(java.time.ZoneId.systemDefault())
    ): UserToken {
        val user = (userRepository.findByUserLoginId(userLoginId)
            ?: throw CustomBadRequestException(ErrorCode.NOT_FOUND_ENTITY))
        return UserToken.create(
            tbUser = user,
            refreshToken = refreshToken,
            remoteAddr = "",
            expiresAt = expiresAt
        ).let { userTokenRepository.save(it) }
    }

    fun createAdmin(userId: String, password: String, passwordEncoder: PasswordEncoder): TbUser {
        return TbUser(
            userLoginId = userId,
            name = "Test Admin",
            password = passwordEncoder.encode(password),
            role = Role.ROLE_ADMIN,
            LoanInfo()
        ).let { user -> userRepository.save(user) }
    }

    fun generateToken(passwordEncoder: PasswordEncoder, jwtUtil: JwtUtil
    , role: Role = Role.ROLE_USER): AuthTokenResponse {
        val user = this.createUser("testUser", "password123", passwordEncoder, role = role)
        return jwtUtil.generateToken(user.userLoginId)
    }

    fun createBookLoan(book: Book, token: String, dueDate: ZonedDateTime? = null): BookLoan {
        val user = userRepository.findByUserLoginId(
            jwtUtil.extractSubject(token)
        ) ?: throw CustomBadRequestException(ErrorCode.NOT_FOUND_ENTITY)

        return BookLoan.loan(book, user, false)
            .also {
                if (dueDate != null) it.dueDate = dueDate
                book.decreaseStock()
                bookRepository.save(book)
            }
            .let(bookLoanRepository::save)
    }

    fun createOverdueBookLoan(book: Book, token: String, dueDate: ZonedDateTime? = null): BookLoan {
        val user = userRepository.findByUserLoginId(
            jwtUtil.extractSubject(token)
        ) ?: throw CustomBadRequestException(ErrorCode.NOT_FOUND_ENTITY)

        return BookLoan.loan(book, user, false)
            .also { if (dueDate != null) it.dueDate = dueDate }
            .let(bookLoanRepository::save)
    }
}