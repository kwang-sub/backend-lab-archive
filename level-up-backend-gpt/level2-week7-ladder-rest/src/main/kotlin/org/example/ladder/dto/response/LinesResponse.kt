package org.example.ladder.dto.response

import org.example.ladder.domain.Line

data class LinesResponse(
    val points: List<PointResponse>,
) {
    companion object {
        fun toDto(line: Line): LinesResponse {
            val pointsResponse = line.points.map { PointResponse.toDto(it) }
            return LinesResponse(pointsResponse)
        }
    }
}