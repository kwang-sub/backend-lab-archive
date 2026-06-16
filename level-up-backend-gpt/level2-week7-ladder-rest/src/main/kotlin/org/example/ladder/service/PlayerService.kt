package org.example.ladder.service

import org.example.ladder.constant.ServiceErrorCode
import org.example.ladder.dto.request.PlayerCommand
import org.example.ladder.dto.response.PageResponse
import org.example.ladder.dto.response.PlayerResponse
import org.example.ladder.exception.ServiceException
import org.example.ladder.factory.DomainFactory
import org.example.ladder.repository.PlayerRepository
import org.example.ladder.service.validator.PlayerValidator
import org.springframework.stereotype.Service

@Service
class PlayerService(
    private val playerRepository: PlayerRepository,
    private val playerValidator: PlayerValidator,
) {
    fun createBulk(players: List<PlayerCommand>): PageResponse<PlayerResponse> {
        if (playerValidator.isNotExistPlayer())
            throw ServiceException(ServiceErrorCode.ALREADY_PLAYER)

        return players.map { DomainFactory.createPlayer(it.name) }
            .forEach(playerRepository::save)
            .let { findAll() }
    }


    fun findAll(): PageResponse<PlayerResponse> {
        return playerRepository.findAll()
            .map(PlayerResponse::toDto)
            .let { PageResponse(it, it.size.toLong(), 1, 0) }
    }
}