package org.example.lotto.view

import org.example.lotto.domain.LotteryBundle
import org.example.lotto.domain.Rank

class OutputView {

    fun printWinningResult(resultLotteryBundle: LotteryBundle) {
        println("당첨 통계")
        println("---------")
        val resultGroup = resultLotteryBundle.getLotteryInfoGroupByRank()

        resultGroup.let { groupedRanks ->
            Rank.entries.forEach { rank ->
                val count = groupedRanks[rank]?.size ?: 0
                println("${rank.getMatchCount()}개 일치 (${rank.getPrize()}원) - ${count}개 ")
            }
        }

        println("총 수익률은 ${resultLotteryBundle.getRevenueRate()}% 입니다.")
    }
}