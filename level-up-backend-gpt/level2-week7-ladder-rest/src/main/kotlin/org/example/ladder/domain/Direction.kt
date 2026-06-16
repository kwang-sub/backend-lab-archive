package org.example.ladder.domain

enum class Direction {
    RIGHT,
    LEFT;

    fun move(currentIdx: Int): Int {
        return when (this) {
            RIGHT -> currentIdx + 1
            LEFT -> currentIdx - 1
        }
    }

    fun canMove(currentIdx: Int, points: List<Point>): Boolean {
        return when (this) {
            RIGHT -> currentIdx < points.size && points[currentIdx].isRightConnect
            LEFT -> currentIdx > 0 && points[currentIdx - 1].isRightConnect
        }
    }
}