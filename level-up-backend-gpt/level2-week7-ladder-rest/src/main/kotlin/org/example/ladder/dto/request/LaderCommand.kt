package org.example.ladder.dto.request

import jakarta.validation.constraints.Min

data class LaderCommand(

    @get: Min(value = 1)
    val lineCount: Int
)