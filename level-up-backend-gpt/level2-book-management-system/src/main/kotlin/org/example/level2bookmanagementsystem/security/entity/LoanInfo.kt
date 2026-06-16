package org.example.level2bookmanagementsystem.security.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.ZonedDateTime

@Embeddable
class LoanInfo(
    @Column(name = "next_loan_available_date", nullable = false)
    var nextLoanAvailableDate: ZonedDateTime = ZonedDateTime.now()
)