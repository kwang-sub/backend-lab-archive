package org.example.lotto.service

import org.example.lotto.domain.LotteryBundle
import org.example.lotto.domain.WinningNumbers

class LotteryCalculator {
    fun calculateLotteryCount(amount: Int): Int {
        if (amount <= 0 || amount % 1000 != 0)
            throw IllegalArgumentException("올바른 금액이 아닙니다.")

        return amount / LotteryBundle.AMOUNT
    }

    fun calculateLotteryWinning(winningLotteryNumber: WinningNumbers, lotteryBundle: LotteryBundle): LotteryBundle {
        lotteryBundle.lotteryTicketList.forEach { lotteryInfo ->
            val rank = lotteryInfo.lotteryNumber.determineRankBy(winningLotteryNumber)
            lotteryInfo.setRank(rank)
        }
        return lotteryBundle
    }
}