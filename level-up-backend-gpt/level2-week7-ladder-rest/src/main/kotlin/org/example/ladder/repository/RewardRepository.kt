package org.example.ladder.repository

import org.example.ladder.domain.Reward
import org.springframework.stereotype.Repository

@Repository
class RewardRepository : Reader<Reward> {

    private val rewards = mutableListOf<Reward>()

    fun save(reward: Reward) {
        rewards.add(reward)
    }

    override fun findAll(): List<Reward> {
        return rewards.toList()
    }
}