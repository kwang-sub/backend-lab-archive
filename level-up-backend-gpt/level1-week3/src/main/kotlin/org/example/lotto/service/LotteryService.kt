package org.example.lotto.service

import org.example.lotto.domain.LotteryBundle
import org.example.lotto.domain.LotteryNumber
import org.example.lotto.domain.LotteryTicket
import org.example.lotto.domain.WinningNumbers
import org.example.org.example.lotto.NumberGenerator
import org.example.org.example.lotto.service.LotteryNumberStrategy

class LotteryService(
    private val lotteryCalculator: LotteryCalculator
) {

    fun issueWinningLotteryNumber(inputWinningNumbers: List<Int>): WinningNumbers {
        return WinningNumbers(inputWinningNumbers)
    }


    fun issueLotteryBundle(
        amount: Int,
        lotteryTickets: List<LotteryTicket>,
    ): LotteryBundle {
        return LotteryBundle(amount, lotteryTickets)
    }

    fun issueLotteryNumber(
        lotteryNumberStrategy: LotteryNumberStrategy
    ): LotteryNumber {

        return lotteryNumberStrategy.generateNumber()
    }

    fun determineWinningRanks(
        winningLotteryNumber: WinningNumbers,
        lotteryBundle: LotteryBundle
    ): LotteryBundle {
        return lotteryCalculator.calculateLotteryWinning(winningLotteryNumber, lotteryBundle)
    }

    fun assignBonusNumber(lotteryNumber: LotteryNumber, bonusNumber: Int) {
        lotteryNumber.setBonusNumber(bonusNumber)
    }

    fun issueManualStrategy(manualNumber: List<Int>): LotteryNumberStrategy.ManualStrategy {
        return LotteryNumberStrategy.ManualStrategy(manualNumber)
    }

    fun calculateLotteryCount(amount: Int): Int {
        return lotteryCalculator.calculateLotteryCount(amount)
    }

    fun calculateAutoLotteryCount(lotteryCount: Int, manualLotteryCount: Int): Int {
        return lotteryCount - manualLotteryCount
    }

    fun issueLotteryTicket(lotteryNumber: LotteryNumber): LotteryTicket {
        return LotteryTicket(lotteryNumber, NumberGenerator.generateId())
    }

    fun plusManualAndAuto(
        manualLotteryNumber: List<LotteryNumber>,
        autoLotteryNumber: List<LotteryNumber>
    ): List<LotteryNumber> {
        return manualLotteryNumber.plus(autoLotteryNumber)
    }

    fun issueLotteryTicketAndAssignBonusNumbers(
        number: LotteryNumber,
        bonusNumber: Int
    ): LotteryTicket {
        this.assignBonusNumber(number, bonusNumber)

        return this.issueLotteryTicket(number)
    }
}