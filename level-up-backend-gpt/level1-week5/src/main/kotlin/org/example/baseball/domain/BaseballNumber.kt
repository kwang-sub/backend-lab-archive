package org.example.baseball.domain

import org.example.baseball.constant.ErrorMessage

class BaseballNumber(
    first: Int, second: Int, third: Int
) {
    val numbers: List<Int>

    init {
        val tempNumbers: List<Int> = listOf(first, second, third)
        require(tempNumbers.all { it in 1..9 }) { ErrorMessage.INVALID_BASEBALL_NUMBER }
        require(tempNumbers.distinct().size == 3) { ErrorMessage.DUPLICATE_BASEBALL_NUMBER }

        this.numbers = tempNumbers
    }

    fun isStrikeAt(index: Int, number: Int): Boolean {
        return this.numbers[index] == number
    }

    fun contains(number: Int): Boolean {
        return this.numbers.contains(number)
    }
}