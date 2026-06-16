package org.example.ladder.domain

import org.example.ladder.constant.ErrorMessage

class Line(val points: List<Point>) {
    init {
        require(points.size >= 1) { ErrorMessage.Companion.INVALID_LINE_MINIMUM_POINT_COUNT }
    }
}