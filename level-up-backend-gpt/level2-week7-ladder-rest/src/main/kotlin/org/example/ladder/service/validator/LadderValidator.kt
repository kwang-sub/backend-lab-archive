package org.example.ladder.service.validator

import org.example.ladder.domain.Ladder
import org.example.ladder.domain.Player
import org.example.ladder.domain.Reward
import org.example.ladder.repository.Reader
import org.springframework.stereotype.Component

@Component
class LadderValidator(
    private val playerReader: Reader<Player>,
    private val rewardReader: Reader<Reward>,
    private val ladderReader: Reader<Ladder> // Assuming a generic reader for ladders
) {
    fun isInvalidGameSetup(): Boolean {
        val players = playerReader.findAll()
        val rewards = rewardReader.findAll()
        return players.isEmpty() || players.size != rewards.size
    }

    fun isExistLadder(): Boolean {
        return ladderReader.findAll().isNotEmpty()
    }
}