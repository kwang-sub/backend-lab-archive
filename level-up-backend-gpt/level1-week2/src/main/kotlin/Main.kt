package org.example

import org.example.car.InputView
import org.example.car.OutputView
import org.example.car.RacingGame
import org.example.car.RandomNumGenerator

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val randomNumGenerator = RandomNumGenerator()

    val cars = inputView.inputCar()
    val raceCount = inputView.inputRaceCount()

    val racingGame = RacingGame(cars, raceCount, randomNumGenerator)

    for (i in 1..raceCount) {
        racingGame.race()
        outputView.printRace(cars)
    }

    outputView.printWinner(racingGame.getWinners())
}