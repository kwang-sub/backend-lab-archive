package org.example.level2bookmanagementsystem.jpa.entity

import jakarta.persistence.*
import org.example.level2bookmanagementsystem.jpa.status.BookLoanStatus
import org.example.level2bookmanagementsystem.security.entity.TbUser
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

@Entity
class BookLoan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Enumerated(EnumType.STRING)
    var status: BookLoanStatus,

    var loanDate: ZonedDateTime, // 대출일

    var dueDate: ZonedDateTime, // 반납 예정일

    var returnDate: ZonedDateTime?, // 실제 반납일

    var renewCount: Int = 0, // 연장 횟수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    val book: Book,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: TbUser,
) : BaseEntity() {
    fun returned(): BookLoan {
        this.returnDate = ZonedDateTime.now()
        this.status = BookLoanStatus.RETURNED

        return this
    }

    fun calculateLateReturnDays(): Long {
        return this.returnDate?.let {
            ChronoUnit.DAYS.between(this.dueDate, it).coerceAtLeast(0)
        } ?: 0L
    }

    companion object {
        fun loan(book: Book, user: TbUser, isRenew: Boolean): BookLoan {
            val dueDate = ZonedDateTime.now().plusDays(DEFAULT_LOAN_DATE)
            return BookLoan(
                status = BookLoanStatus.LOANED,
                loanDate = ZonedDateTime.now(),
                dueDate = if (isRenew) dueDate.plusDays(DEFAULT_DUE_DATE) else dueDate,
                returnDate = null,
                renewCount = if (isRenew) 1 else 0,
                book = book,
                user = user,
            )
        }

        val MAX_RENEW_COUNT = 1 // 최대 연장 횟수
        val DEFAULT_LOAN_DATE = 14L // 기본 대출 기간 (14일)
        val DEFAULT_DUE_DATE = 7L // 연장 시 추가 대출 기간 (7일)
        val MAX_LOANED_BOOK_COUNT = 5 // 최대 대출 가능 도서 수
    }
}