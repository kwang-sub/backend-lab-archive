package org.example.baseball.factory

import org.example.baseball.domain.BaseballNumber

class BaseballNumberGenerator {
    fun generate(): BaseballNumber {
        val numberRange = 1..9

        val first = numberRange.random()
        val second = numberRange.random()
        val third = numberRange.random()

        return BaseballNumber(first, second, third)
    }
}