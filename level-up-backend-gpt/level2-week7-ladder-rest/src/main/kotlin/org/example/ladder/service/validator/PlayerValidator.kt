package org.example.ladder.service.validator

import org.example.ladder.domain.Player
import org.example.ladder.dto.request.RewardCommand
import org.example.ladder.repository.Reader
import org.springframework.stereotype.Component

@Component
class PlayerValidator(
    private val playerReader: Reader<Player>
) {

    fun isNotMatchPlayerCount(commands: List<RewardCommand>): Boolean {
        val players = playerReader.findAll()
        return players.size != commands.size
    }

    fun isNotExistPlayer(): Boolean {
        val players = playerReader.findAll()
        return players.isNotEmpty()
    }
}