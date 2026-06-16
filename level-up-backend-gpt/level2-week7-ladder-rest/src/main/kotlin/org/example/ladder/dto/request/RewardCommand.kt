package org.example.ladder.dto.request

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class RewardCommand(


    @get:NotNull
    @get:Pattern(regexp = "^([^\\s,]{1,10})(,[^\\s,]{1,10})*$")
    val name: String
) {
}