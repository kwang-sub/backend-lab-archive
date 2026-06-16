package org.example.baseball.controller

import org.example.baseball.domain.BaseballNumber
import org.example.baseball.domain.BaseballResult
import org.example.baseball.domain.Judge
import org.example.baseball.factory.BaseballNumberGenerator
import org.example.baseball.view.InputView
import org.example.baseball.view.OutputView

class GameController(
    val judge: Judge,
    val baseballNumberGenerator: BaseballNumberGenerator,
    val inputView: InputView,
    val outputView: OutputView
) {
    fun startGame() {
        outputView.printStartMessage()
        val generatedNumbers = baseballNumberGenerator.generate()

        var result: BaseballResult
        do {
            val baseballNumbers = inputView.inputBaseballNumber()
            val baseballNumber =
                BaseballNumber(baseballNumbers.first, baseballNumbers.second, baseballNumbers.third)

            result = judge.judge(generatedNumbers, baseballNumber)

            outputView.printResult(result)
        } while (!result.isThreeStrike())
    }
}