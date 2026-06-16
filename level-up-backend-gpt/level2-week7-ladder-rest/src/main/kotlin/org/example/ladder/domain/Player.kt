package org.example.ladder.domain

import org.example.ladder.constant.DomainErrorCode
import org.example.ladder.util.requireDomain

data class Player(val name: String) {
    init {
        requireDomain(name.isNotBlank(), DomainErrorCode.INVALID_PLAYER_NAME)
        requireDomain(name.trim().length <= 5, DomainErrorCode.INVALID_PLAYER_NAME)
    }
}