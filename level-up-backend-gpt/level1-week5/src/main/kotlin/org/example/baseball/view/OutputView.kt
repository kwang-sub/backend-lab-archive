package org.example.baseball.view

import org.example.baseball.domain.BaseballResult

class OutputView {
    fun printStartMessage() {
        println("숫자 야구 게임을 시작합니다.")
    }

    fun printResult(result: BaseballResult) {
        val message = if (result.strikeCount == 0 && result.ballCount == 0) "낫싱"
        else if (result.isThreeStrike()) "3스트라이크 \n정답을 맞추셨습니다."
        else "${result.strikeCount}스트라이크 ${result.ballCount}볼"

        println(message)
    }
}