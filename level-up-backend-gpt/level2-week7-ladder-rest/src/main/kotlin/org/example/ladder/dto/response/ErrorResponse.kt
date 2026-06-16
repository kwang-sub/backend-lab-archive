package org.example.ladder.dto.response

import java.time.ZonedDateTime

data class ErrorResponse(
    val code: String,
    val message: String,
    val status: Int,
    val path: String,
    val timestamp: ZonedDateTime,
    var fieldErrors: List<ErrorDetailResponse> = emptyList(),
)

