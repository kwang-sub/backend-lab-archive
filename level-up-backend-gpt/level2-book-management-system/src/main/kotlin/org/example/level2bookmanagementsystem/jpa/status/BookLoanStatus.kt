package org.example.level2bookmanagementsystem.jpa.status

enum class BookLoanStatus {
    LOANED, // 대출 중
    RETURNED, // 반납 완료
    OVERDUE // 연체 중
}