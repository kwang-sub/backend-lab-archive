package org.example.ladder.service

import org.example.ladder.domain.Player
import org.example.ladder.dto.request.RewardCommand
import org.example.ladder.dto.response.PageResponse
import org.example.ladder.dto.response.RewardResponse
import org.example.ladder.exception.EntityNotFoundException
import org.example.ladder.factory.DomainFactory
import org.example.ladder.repository.RewardRepository
import org.example.ladder.service.validator.PlayerValidator
import org.springframework.stereotype.Service

@Service
class RewardService(
    private val rewardRepository: RewardRepository,
    private val playerValidator: PlayerValidator,
) {

    fun createBulk(commands: List<RewardCommand>): PageResponse<RewardResponse> {
        if (playerValidator.isNotMatchPlayerCount(commands))
            throw EntityNotFoundException(Player::class.java)

        return commands.map { DomainFactory.createReward(it.name) }
            .map { rewardRepository.save(it) }
            .let { findAll() }
    }

    private fun findAll(): PageResponse<RewardResponse> {
        return rewardRepository.findAll()
            .map(RewardResponse::toDto)
            .let { PageResponse(it, it.size.toLong(), 1, 0) }
    }
}