package org.example.ladder.domain

import org.example.ladder.constant.ErrorMessage

class Ladder(val lines: List<Line>) {
    init {
        require(1 == lines.distinctBy { it.points.size }.size) { ErrorMessage.Companion.NOT_SAME_SIZE_LINES }
    }
}