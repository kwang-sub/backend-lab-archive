package org.example.ladder.view

import org.example.ladder.constant.ErrorMessage
import org.example.ladder.domain.Player

class InputView(
    private val validator: Validator,
) {

    fun inputPlayerNames(): List<String> {
        println("플레이어 이름을 입력하세요(예 : 홍길동,이순신,강감찬)")
        val input = readLine()
        validator.validatePlayerNames(input)

        return input?.split(",")?.map { it.trim() } ?: emptyList()
    }

    fun inputRewords(size: Int): List<String> {
        println("보상 내역 ${size}개를 입력하세요(예 : 1,2,3,4)")
        val input = readLine()
        validator.validateRewords(input, size)

        return input?.split(",")?.map { it.trim() } ?: emptyList()
    }

    fun inputLayerCount(): Int {
        println("사다리 층 수를 입력하세요(예 : 3)")
        val input = readLine()

        return input?.toInt() ?: throw IllegalArgumentException(ErrorMessage.INVALID_LAYER_COUNT)
    }

    fun inputTargetPlayer(players: List<Player>): String {
        println("대상 플레이어를 입력하세요(예 : ${players.joinToString(",")})")
        val input = readLine() ?: ""
        validator.validateTargetPlayer(input, players)

        return input
    }

    fun inputExit(): Boolean {
        println("게임을 종료하시겠습니까? (y/n)")
        val input = readLine() ?: ""
        return input.lowercase() == "y"
    }
}

