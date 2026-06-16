package org.example.ladder.domain

import org.example.ladder.constant.DomainErrorCode
import org.example.ladder.util.requireDomain

data class Ladder(val lines: List<Line>) {
    init {
        requireDomain(1 == lines.distinctBy { it.points.size }.size, DomainErrorCode.NOT_SAME_SIZE_LINES)
    }
}