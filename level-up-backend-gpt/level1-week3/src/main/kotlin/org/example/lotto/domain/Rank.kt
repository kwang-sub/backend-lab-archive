package org.example.lotto.domain

enum class Rank {

    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    private val matchCount: Int
    private val prize: Int

    constructor(matchCount: Int, prize: Int) {
        this.matchCount = matchCount
        this.prize = prize
    }

    fun getMatchCount(): Int {
        return matchCount
    }

    fun getPrize(): Int {
        return prize
    }
}