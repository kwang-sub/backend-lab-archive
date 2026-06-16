package org.example.ladder.factory

import org.example.ladder.domain.*

class DomainFactory {
    companion object {
        fun createPlayer(name: String): Player {
            return Player(name)
        }

        fun createPoint(isRightConnect: Boolean): Point {
            return Point(isRightConnect)
        }

        fun createLine(points: List<Point>): Line {
            return Line(points)
        }

        fun createReword(rewordName: String): Reword {
            return Reword(rewordName)
        }

        fun createLadder(lineCount: Int, pointCount: Int): Ladder {
            val lines = IntRange(1, lineCount)
                .map {
                    val pointList = createPointList(pointCount)
                    createLine(pointList)
                }

            return Ladder(lines)
        }

        private fun createPointList(pointCount: Int): List<Point> {
            return IntRange(1, pointCount)
                .map { createPoint(createRandomBoolean()) }
        }

        private fun createRandomBoolean(): Boolean {
            return (0..1).random() == 1
        }
    }
}

