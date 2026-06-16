package org.example.ladder.domain

import org.example.ladder.constant.ErrorMessage

class Player(val name: String) {
    init {
        require(name.isNotBlank()) { ErrorMessage.INVALID_PLAYER_NAME }
        require(name.trim().length <= 5) { ErrorMessage.INVALID_PLAYER_NAME }
    }
}