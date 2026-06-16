package org.example.level2bookmanagementsystem.base.dto.command

import jakarta.validation.constraints.NotNull
import java.io.Serializable

data class BookLoanCommand(
    @get:NotNull
    val bookId: Long,
    @get:NotNull
    val isRenew: Boolean,
) : Serializable