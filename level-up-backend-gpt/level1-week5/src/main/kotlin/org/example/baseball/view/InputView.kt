package org.example.baseball.view

class InputView(
    val validator: Validator
) {
    fun inputBaseballNumber(): Triple<Int, Int, Int> {
        println("1 ~ 9까지 서로 다른 숫자 3개를 입력해주세요(예: 1,2,3)")
        val input = readLine()
        validator.validateBaseballNumber(input, ",")

        return input?.split(",")
            ?.map { it.trim().toInt() }
            ?.let { Triple(it[0], it[1], it[2]) }
            ?: throw IllegalArgumentException("Invalid input")
    }
}