package org.example.ladder.dto.response

import org.example.ladder.domain.Ladder

data class LadderResponse(
    val lines: List<LinesResponse>,
) {
    companion object {
        fun toDto(ladder: Ladder): LadderResponse {
            val linesResponses = ladder.lines.map { LinesResponse.toDto(it) }
            return LadderResponse(linesResponses)
        }
    }
}