package org.example.ladder.dto.response

import org.example.ladder.domain.Reward

data class RewardResponse(
    val name: String
) {
    companion object {

        fun toDto(reward: Reward): RewardResponse {
            return RewardResponse(
                name = reward.name
            )
        }
    }
}