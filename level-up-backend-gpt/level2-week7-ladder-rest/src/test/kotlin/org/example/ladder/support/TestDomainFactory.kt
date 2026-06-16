package org.example.ladder.support

import org.example.ladder.domain.Ladder
import org.example.ladder.domain.Player
import org.example.ladder.domain.Point
import org.example.ladder.domain.Reward
import org.example.ladder.factory.DomainFactory.Companion.createLine
import org.example.ladder.factory.DomainFactory.Companion.createPoint
import org.example.ladder.repository.LadderRepository
import org.example.ladder.repository.PlayerRepository
import org.example.ladder.repository.RewardRepository

class TestDomainFactory(
    private val playerRepository: PlayerRepository,
    private val rewardRepository: RewardRepository,
    private val ladderRepository: LadderRepository,
) {
    fun savePlayer(nameList: List<String>): List<Player> {
        return nameList.map { Player(it) }
            .onEach(playerRepository::save)
    }

    fun saveReward(nameList: List<String>): List<Reward> {
        return nameList.map { Reward(it) }
            .onEach(rewardRepository::save)
    }

    fun saveLadder(lineCount: Int, pointCount: Int): Ladder {
        val ladder = createLadder(lineCount, pointCount)
        return ladderRepository.save(ladder)
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