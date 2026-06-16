package org.example.baseball

import org.example.baseball.controller.GameController
import org.example.baseball.domain.Judge
import org.example.baseball.factory.BaseballNumberGenerator
import org.example.baseball.view.InputView
import org.example.baseball.view.OutputView
import org.example.baseball.view.Validator

fun main() {
    val validator = Validator()
    val gameController = GameController(
        judge = Judge(),
        baseballNumberGenerator = BaseballNumberGenerator(),
        inputView = InputView(validator),
        outputView = OutputView()
    )

    gameController.startGame()
}