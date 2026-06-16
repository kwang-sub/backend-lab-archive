package org.example.ladder.domain

import org.example.ladder.constant.DomainErrorCode
import org.example.ladder.util.requireDomain

data class Line(val points: List<Point>) {
    init {
        requireDomain(points.size >= 1, DomainErrorCode.INVALID_LINE_MINIMUM_POINT_COUNT)
    }
}