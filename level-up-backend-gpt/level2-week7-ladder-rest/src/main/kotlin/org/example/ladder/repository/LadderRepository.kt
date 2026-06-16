package org.example.ladder.repository

import org.example.ladder.domain.Ladder
import org.springframework.stereotype.Repository

@Repository
class LadderRepository : Reader<Ladder> {
    private var ladder: Ladder? = null

    fun save(ladder: Ladder): Ladder {
        this.ladder = ladder
        return ladder
    }

    override fun findOne(): Ladder? {
        return ladder
    }

    override fun findAll(): List<Ladder> {
        return ladder?.let { listOf(it) } ?: emptyList()
    }
}