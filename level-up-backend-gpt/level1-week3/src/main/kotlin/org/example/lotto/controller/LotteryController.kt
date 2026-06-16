package org.example.lotto.controller

import org.example.lotto.service.LotteryService
import org.example.lotto.view.InputView
import org.example.lotto.view.OutputView
import org.example.org.example.lotto.service.LotteryNumberStrategy

class LotteryController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lotteryService: LotteryService
) {

    fun startLotteryGame() {
        val amount = inputView.inputAmount()
        val lotteryCount = lotteryService.calculateLotteryCount(amount)
        val manulNumberList = inputView.inputManualNumberList(lotteryCount)
        val autoLotteryCount =  lotteryService.calculateAutoLotteryCount(lotteryCount, manulNumberList.size)

        val manualLotteryNumber = manulNumberList
            .map { lotteryService.issueManualStrategy(it) }
            .map { lotteryService.issueLotteryNumber(it) }

        val autoLotteryNumber = (1..autoLotteryCount)
            .map { lotteryService.issueLotteryNumber(LotteryNumberStrategy.AutoStrategy()) }

        val lotteryNumbers = lotteryService.plusManualAndAuto(manualLotteryNumber, autoLotteryNumber)
        val lotteryTickets = lotteryNumbers.map {
            val bonusNumber = inputView.inputBonusNumber(it)
            lotteryService.issueLotteryTicketAndAssignBonusNumbers(it, bonusNumber)
        }

        val lotteryBundle = lotteryService.issueLotteryBundle(amount, lotteryTickets)

        val inputWinningNumbers = inputView.inputWinningNumbers()
        val winningLotteryNumber = lotteryService.issueWinningLotteryNumber(inputWinningNumbers)
        val inputBonusNumber = inputView.inputBonusNumber(winningLotteryNumber)
        lotteryService.assignBonusNumber(winningLotteryNumber, inputBonusNumber)

        val resultLottery = lotteryService.determineWinningRanks(winningLotteryNumber, lotteryBundle)
        outputView.printWinningResult(resultLottery)
    }
}