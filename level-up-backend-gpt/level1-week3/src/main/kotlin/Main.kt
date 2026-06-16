package org.example

import org.example.lotto.config.AppFactory


fun main() {
    val appFactory = AppFactory()
    val lotteryController = appFactory.createLotteryController()

    lotteryController.startLotteryGame()
}