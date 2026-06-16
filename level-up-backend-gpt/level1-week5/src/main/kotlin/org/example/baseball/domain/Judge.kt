package org.example.baseball.domain

class Judge {
    fun judge(baseBallNumber: BaseballNumber, userInputNumber: BaseballNumber): BaseballResult {
        var strikeCount = 0
        var ballCount = 0

        userInputNumber.numbers.forEachIndexed { index, number ->
            if (baseBallNumber.isStrikeAt(index, number) ) strikeCount++
            else if (baseBallNumber.contains(number)) ballCount++
        }

        return BaseballResult(strikeCount, ballCount)
    }
}