package org.example.car

class RacingGame(
    val cars: List<Car>,
    val totalRound: Int,
    val randomNumGenerator: RandomNumGenerator,
) {

    var currentRound = 0

    fun race() {
        if (totalRound <= currentRound++)
            throw IllegalStateException("경주가 끝났습니다.")
        cars.forEach { it.drive(randomNumGenerator.generate()) }
    }

    fun getWinners(): List<Car> {
        if (totalRound > currentRound)
            throw IllegalStateException("경주가 끝나지 않았습니다.")

        val maxDistance = cars.maxOf { it.distance }

        return cars.filter { it.distance == maxDistance }.toList()
    }
}