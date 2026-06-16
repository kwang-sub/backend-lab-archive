package org.example.ladder.dto.response

import org.example.ladder.domain.Point

data class PointResponse(
    val isRightConnect: Boolean
) {
    companion object {
        fun toDto(point: Point): PointResponse {
            return PointResponse(isRightConnect = point.isRightConnect)
        }
    }
}