package org.example.ladder.service

import org.example.ladder.constant.DomainErrorCode
import org.example.ladder.domain.*
import org.example.ladder.util.requireDomain
import org.springframework.stereotype.Service

@Service
class LadderGameService {

    fun playGame(
        ladder: Ladder,
        players: List<Player>,
        rewards: List<Reward>,
        targetPlayerName: String
    ): Reward {
        val playerIdx = getPlayerIdx(players, targetPlayerName)
        requireDomain(playerIdx >= 0, DomainErrorCode.INVALID_TARGET_PLAYER)
        val resultIdx = getLadderResultIdx(ladder, playerIdx)

        return rewards[resultIdx]
    }

    private fun getLadderResultIdx(ladder: Ladder, startIdx: Int): Int {
        var direction = getStartDirection(ladder.lines, startIdx)
        var currentIdx = startIdx

        ladder.lines.forEach {
            currentIdx = getLadderIdxByPoints(it.points, direction, currentIdx)
            if (currentIdx == 0) direction = Direction.RIGHT
            else if (currentIdx == it.points.size) direction = Direction.LEFT
        }

        return currentIdx
    }

    private fun getLadderIdxByPoints(
        points: List<Point>,
        direction: Direction,
        startIdx: Int
    ): Int {
        var currentIdx = startIdx

        while (direction.canMove(currentIdx, points))
            currentIdx = direction.move(currentIdx)

        return currentIdx
    }

    private fun getStartDirection(lines: List<Line>, startIdx: Int): Direction {
        lines.forEach { line ->
            if (isRightConnect(line.points, startIdx)) return Direction.RIGHT
            else if (isLeftConnect(line.points, startIdx)) return Direction.LEFT
        }

        return Direction.RIGHT
    }

    private fun isLeftConnect(
        points: List<Point>,
        startIdx: Int
    ): Boolean {
        if (startIdx <= 0) return false
        return points[startIdx - 1].isRightConnect
    }

    private fun isRightConnect(
        points: List<Point>,
        startIdx: Int
    ): Boolean {
        if (startIdx >= points.size) return false
        return points[startIdx].isRightConnect
    }

    private fun getPlayerIdx(
        players: List<Player>,
        targetPlayerName: String
    ): Int {
        return players.indexOfFirst { it.name == targetPlayerName }
    }
}