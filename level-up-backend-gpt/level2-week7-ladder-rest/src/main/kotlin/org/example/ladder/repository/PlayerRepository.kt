package org.example.ladder.repository

import org.example.ladder.domain.Player
import org.springframework.stereotype.Component

@Component
class PlayerRepository: Reader<Player> {

    private val players = mutableListOf<Player>()

    fun save(player: Player) {
        players.add(player)
    }

    override fun findAll(): List<Player> {
        return players.toList()
    }
}