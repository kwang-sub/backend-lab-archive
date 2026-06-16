package org.example.ladder.service

import org.example.ladder.constant.ServiceErrorCode
import org.example.ladder.domain.Ladder
import org.example.ladder.domain.Player
import org.example.ladder.domain.Reward
import org.example.ladder.dto.response.LadderResponse
import org.example.ladder.dto.response.RewardResponse
import org.example.ladder.exception.EntityNotFoundException
import org.example.ladder.exception.ServiceException
import org.example.ladder.factory.DomainFactory
import org.example.ladder.repository.LadderRepository
import org.example.ladder.repository.Reader
import org.example.ladder.service.validator.LadderValidator
import org.springframework.stereotype.Service

@Service
class LadderService(
    private val playerReader: Reader<Player>,
    private val rewardReader: Reader<Reward>,
    private val ladderValidator: LadderValidator,
    private val ladderRepository: LadderRepository,
    private val ladderGameService: LadderGameService,
) {

    fun createLadder(lineCount: Int): LadderResponse {
        if (ladderValidator.isExistLadder())
            throw ServiceException(ServiceErrorCode.ALREADY_EXIST_LADDER)
        if (ladderValidator.isInvalidGameSetup())
            throw ServiceException(ServiceErrorCode.MISMATCHED_PLAYER_AND_REWARD_COUNT)

        val players = playerReader.findAll()
        val ladder = DomainFactory.createLadder(lineCount, players.size - 1)
            .let(ladderRepository::save)

        return LadderResponse.toDto(ladder)
    }

    fun getResult(name: String): RewardResponse {

        val players = playerReader.findAll()
        if (players.isEmpty()) {
            throw EntityNotFoundException(Player::class.java)
        }

        val ladder = ladderRepository.findOne() ?: throw EntityNotFoundException(Ladder::class.java)

        val targetPlayer = players.find { it.name == name }
            ?: throw EntityNotFoundException(Player::class.java)

        val rewards = rewardReader.findAll()
        if (rewards.isEmpty()) {
            throw EntityNotFoundException(Reward::class.java)
        }

        val resultReward = ladderGameService.playGame(ladder, players, rewards, name)

        return RewardResponse.toDto(resultReward)
    }

    fun getLadder(): LadderResponse {
        val ladder = ladderRepository.findOne() ?: throw EntityNotFoundException(Ladder::class.java)
        return LadderResponse.toDto(ladder)
    }

}