package org.example.car

import java.io.BufferedReader
import java.io.InputStreamReader

class InputView {

    fun inputCar(): List<Car> {
        println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,) 기준으로 구분)")
        val br = BufferedReader(InputStreamReader(System.`in`))
        val inputCarName = br.readLine()
        if (inputCarName.isNullOrBlank())
            throw IllegalArgumentException("잘못된 자동차 이름입니다.")

        val carNames = inputCarName.split(",")

        return carNames.map { Car(it) }
    }

    fun inputRaceCount(): Int {
        println("시도할 회수는 몇 회인가요?")
        val br = BufferedReader(InputStreamReader(System.`in`))

        return br.readLine().toIntOrNull() ?: throw IllegalArgumentException("숫자 형식이 아닙니다.")
    }
}