package org.example.ladder

import org.example.ladder.controller.LadderGameController
import org.example.ladder.controller.OutputView
import org.example.ladder.service.LadderGameService
import org.example.ladder.view.InputView
import org.example.ladder.view.Validator

fun main() {
    val validator = Validator()
    val inputView = InputView(validator)
    val outputView = OutputView()
    val ladderGameService = LadderGameService()
    val controller = LadderGameController(inputView, outputView, ladderGameService)
    controller.startGame()
}