package org.example.org.example.lotto.service

import org.example.lotto.domain.LotteryNumber
import java.security.SecureRandom

sealed class LotteryNumberStrategy {

    abstract fun generateNumber(): LotteryNumber

    class AutoStrategy : LotteryNumberStrategy() {
        override fun generateNumber(): LotteryNumber {
            val numbers = (1..45).toMutableList()
            val secureRandom = SecureRandom()
            numbers.shuffle(secureRandom)

            return LotteryNumber(numbers.take(6))
        }
    }

    class ManualStrategy(private val numbers: List<Int>) : LotteryNumberStrategy() {
        override fun generateNumber(): LotteryNumber {
            return LotteryNumber(numbers)
        }
    }
}