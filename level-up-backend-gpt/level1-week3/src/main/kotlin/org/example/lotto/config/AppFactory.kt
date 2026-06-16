package org.example.lotto.config

import org.example.lotto.controller.LotteryController
import org.example.lotto.service.LotteryCalculator
import org.example.lotto.service.LotteryService
import org.example.lotto.view.InputView
import org.example.lotto.view.OutputView

class AppFactory {

    private var inputView: InputView? = null
    private var outputView: OutputView? = null
    private var lotteryController: LotteryController? = null
    private var lotteryService: LotteryService? = null
    private var lotteryCalculator: LotteryCalculator? = null

    fun createInputView(): InputView {
        if (inputView == null) {
            inputView = InputView()
        }

        return inputView!!
    }

    fun createOutputView(): OutputView {
        if (outputView == null) {
            outputView = OutputView()
        }

        return outputView!!
    }

    fun createLotteryController(): LotteryController {
        if (lotteryController == null) {
            lotteryController = LotteryController(
                createInputView(),
                createOutputView(),
                createLotteryService()
            )
        }

        return lotteryController!!
    }

    fun createLotteryService(): LotteryService {
        if (lotteryService == null) {
            lotteryService = LotteryService(createLotteryCalculator())
        }

        return lotteryService!!
    }

    fun createLotteryCalculator(): LotteryCalculator {
        if (lotteryCalculator == null) {
            lotteryCalculator = LotteryCalculator()
        }

        return lotteryCalculator!!
    }
}