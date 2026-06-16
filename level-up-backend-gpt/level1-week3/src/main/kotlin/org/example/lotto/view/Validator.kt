package org.example.lotto.view

class Validator {

    fun validateAndReturnAmount(amount: Int?): Int {
        if (amount == null || amount <= 0 || amount % 1000 != 0)
            throw IllegalArgumentException("올바른 금액이 아닙니다.")

        return amount
    }

    fun validateAndReturnBonusNumber(bonusNumber: Int?): Int {
        if (bonusNumber == null || bonusNumber < 1 || bonusNumber > 45)
            throw IllegalArgumentException("올바른 보너스 번호가 아닙니다.")

        return bonusNumber
    }

    fun validateAndReturnLotteryNumbers(lotteryNumbers: String?, delimiters: Char): String {
        if (lotteryNumbers.isNullOrBlank())
            throw IllegalArgumentException("올바른 복권 번호가 아닙니다.")

        val regex = Regex("^([1-9]|[1-3][0-9]|4[0-5])($delimiters([1-9]|[1-3][0-9]|4[0-5])){5}\$")
        if (!regex.matches(lotteryNumbers))
            throw IllegalArgumentException("올바른 복권 번호가 아닙니다.")

        return lotteryNumbers
    }

    fun validateAndReturnManualCount(manualCount: Int?, lotteryCount: Int): Int {
        if (manualCount == null || manualCount < 0 || manualCount > lotteryCount)
            throw IllegalArgumentException("올바른 수동 구매 매수가 아닙니다.")

        return manualCount
    }
}