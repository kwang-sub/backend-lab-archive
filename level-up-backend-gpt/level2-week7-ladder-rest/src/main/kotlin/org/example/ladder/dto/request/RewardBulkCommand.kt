package org.example.ladder.dto.request

import jakarta.validation.Valid
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

data class RewardBulkCommand(
    @get:Valid
    @get:NotNull
    @get:Size(min = 2)
    val rewards: List<RewardCommand> = emptyList(),
)