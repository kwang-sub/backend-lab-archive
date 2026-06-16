package org.example.car

class OutputView {

    fun printRace(cars: List<Car>) {
        cars.forEach { println("${it.name} : ${"-".repeat(it.distance)}") }
        println()
    }

    fun printWinner(cars: List<Car>) {
        val winners = cars.joinToString(separator = ", ") { it.name }
        println("최종 우승자: $winners")
    }
}