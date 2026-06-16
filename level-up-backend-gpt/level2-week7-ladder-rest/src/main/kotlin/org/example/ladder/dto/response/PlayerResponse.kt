package org.example.ladder.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import org.example.ladder.domain.Player

data class PlayerResponse(

    @Schema(
        description = "플레이어 이름 (1~5자, 쉼표로 구분된 목록 가능)",
        example = "test1,test2",
        defaultValue = "default1"
    )
    @get:NotNull
    @get:Pattern(regexp = "^([^\\s,]{1,5})(,[^\\s,]{1,5})*$")
    val name: String
) {
    companion object{

        fun toDto(player: Player): PlayerResponse {
            return PlayerResponse(
                name = player.name
            )
        }
    }
}