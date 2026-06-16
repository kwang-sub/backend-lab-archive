package org.example.lotto.domain

import org.example.lotto.domain.LotteryNumber

class WinningNumbers(
    val winningNumbers: List<Int>,
) : LotteryNumber(winningNumbers) {
}