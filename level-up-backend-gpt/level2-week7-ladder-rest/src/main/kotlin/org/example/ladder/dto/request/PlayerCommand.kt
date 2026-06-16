package org.example.ladder.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class PlayerCommand(

    @Schema(
        description = "플레이어 이름 (1~5자, 쉼표로 구분된 목록 가능)",
        example = "user1,user2,user3,user4",
        defaultValue = "user",
    )
    @get:NotNull
    @get:Pattern(regexp = "^([^\\s,]{1,5})(,[^\\s,]{1,5})*$")
    val name: String
) {
}