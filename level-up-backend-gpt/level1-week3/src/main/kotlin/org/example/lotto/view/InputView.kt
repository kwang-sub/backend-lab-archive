package org.example.lotto.view

import org.example.lotto.domain.LotteryNumber


class InputView(
    private val validator: Validator = Validator()
) {
    fun inputAmount(): Int {
        println("구입 금액을 입력하세요.")
        val amount = readLine()?.toIntOrNull()
        val checkValidAmount = validator.validateAndReturnAmount(amount)

        return checkValidAmount
    }

    fun inputBonusNumber(lotteryNumber: LotteryNumber): Int {
        println(lotteryNumber)
        println("보너스 번호를 입력하세요.")
        val bonusNumber = readLine()?.toIntOrNull()
        val validateBonusNumber = validator.validateAndReturnBonusNumber(bonusNumber)

        return validateBonusNumber
    }

    fun inputWinningNumbers(): List<Int> {
        println("당첨 번호 6자리를 입력하세요.")
        val inputWinningNumbers = readLine()

        val delimiters = ','
        val validateWinningNumbers =
            validator.validateAndReturnLotteryNumbers(inputWinningNumbers, delimiters)

        return validateWinningNumbers.split(delimiters)
            .map { it.toInt() }
    }

    fun inputManualNumberList(lotteryCount: Int): List<List<Int>> {
        println("수동으로 구매할 매수를 입력하세요.")
        val manualCount = readLine()?.toIntOrNull()
        val validateManualCount = validator.validateAndReturnManualCount(manualCount, lotteryCount)


        return IntRange(1, validateManualCount).map {
            println("수동으로 구매할 번호를 입력하세요.")
            val manualNumber = readLine()
            val delimiters = ','
            val validateLotteryNumbers =
                validator.validateAndReturnLotteryNumbers(manualNumber, delimiters)

            validateLotteryNumbers.split(delimiters)
                .map { it.toInt() }
        }
    }
}