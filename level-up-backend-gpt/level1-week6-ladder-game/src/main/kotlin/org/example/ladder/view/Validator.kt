package org.example.ladder.view

import org.example.ladder.constant.ErrorMessage
import org.example.ladder.domain.Player

class Validator {
    fun validatePlayerNames(input: String?) {
        require(!input.isNullOrBlank()) { ErrorMessage.Companion.INVALID_PLAYER_NAME }
        val regex = """^([^\s,]{1,5})(,[^\s,]{1,5})*$""".toRegex()
        require(regex.matches(input)) { ErrorMessage.Companion.INVALID_PLAYER_NAME }
    }

    fun validateRewords(input: String?, size: Int) {
        require(!input.isNullOrBlank()) { ErrorMessage.Companion.INVALID_REWORD_NAME }
        val regex = """^([^\s,]{1,5})(,[^\s,]{1,5}){${size - 1}}$""".toRegex()
        require(regex.matches(input)) { ErrorMessage.INVALID_REWORD_NAME }
    }

    fun validateTargetPlayer(player: String, players: List<Player>) {
        require(players.map { it.name }.contains(player)) { ErrorMessage.INVALID_TARGET_PLAYER }
    }
}