package org.example.lotto

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.lotto.domain.LotteryBundle
import org.example.lotto.domain.LotteryTicket
import org.example.lotto.domain.LotteryNumber
import org.example.lotto.domain.Rank
import org.example.lotto.service.LotteryCalculator
import org.example.lotto.domain.WinningNumbers
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class LotteryCalculatorTest {

    @Test
    fun 구입_가능한_로또수_계산_정상_테스트() {
        // given
        val amount = 3000
        val lotteryCalculator = LotteryCalculator()

        // when
        val result = lotteryCalculator.calculateLotteryCount(amount)

        // then
        assertThat(result).isEqualTo(3000 / LotteryBundle.AMOUNT)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1000, 0, 1, 1001])
    fun 구입_가능한_로또수_계산_예외_테스트(amount: Int) {
        // given
        val lotteryCalculator = LotteryCalculator()

        // when
        assertThatThrownBy { lotteryCalculator.calculateLotteryCount(amount) }

            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("올바른 금액이 아닙니다.")
    }

    @ParameterizedTest
    @CsvSource(
        delimiter = ';',
        value = [
            "1,2,3,4,5,6;7;1,2,3,4,5,6;45;FIRST",  // 1등
            "1,2,3,4,5,6;7;1,2,3,4,5,45;7;SECOND", // 2등
            "1,2,3,4,5,6;7;1,2,3,4,5,8;45;THIRD",  // 3등
            "1,2,3,4,5,6;7;1,2,3,4,8,9;45;FOURTH", // 4등
            "1,2,3,4,5,6;7;1,2,3,8,9,10;45;FIFTH", // 5등
            "1,2,3,4,5,6;7;8,9,10,11,12,13;45;NONE" // 당첨 안됨
        ]
    )
    fun calculateWinningNumbers_shouldWinningResult_whenAllMatch(
        winningNumbers: String,
        winningBonusNumber: Int,
        lotteryNumbers: String,
        lotteryBonusNumber: Int,
        expectedRank: Rank
    ) {
        // given
        val winningNumbersList = winningNumbers.split(",").map { it.toInt() }
        val lotteryNumbersList = lotteryNumbers.split(",").map { it.toInt() }

        val winningNumbers = WinningNumbers(winningNumbersList)
        winningNumbers.setBonusNumber(winningBonusNumber)

        val lotteryNumbers = LotteryNumber(lotteryNumbersList)
        lotteryNumbers.setBonusNumber(lotteryBonusNumber)
        val lotteryTicket = LotteryTicket(
            lotteryNumber = lotteryNumbers,
            order = 1
        )

        val lotteryBundle = LotteryBundle(1000, listOf(lotteryTicket))

        val lotteryCalculator = LotteryCalculator()
        // when
        val result = lotteryCalculator.calculateLotteryWinning(winningNumbers, lotteryBundle)

        // then
        assertThat(result.getLotteryInfoGroupByRank().size).isEqualTo(1)
        assertThat(result.getRevenueAmount()).isEqualTo(expectedRank.getPrize())
    }

    @Test
    fun calculateWinningNumbers_shouldLoseResult_whenNoMatch() {
        // given
        val winningNumbers = WinningNumbers(listOf(1, 2, 3, 4, 5, 6))
        winningNumbers.setBonusNumber(7)

        val lotteryNumbers = LotteryNumber(listOf(7, 8, 9, 10, 11, 12))
        lotteryNumbers.setBonusNumber(13)
        val lotteryTicket = LotteryTicket(
            lotteryNumber = lotteryNumbers,
            order = 1
        )

        val lotteryBundle = LotteryBundle(1000, listOf(lotteryTicket))

        val lotteryCalculator = LotteryCalculator()
        // when
        val result = lotteryCalculator.calculateLotteryWinning(winningNumbers, lotteryBundle)

        // then
        assertThat(result.getLotteryInfoGroupByRank().size).isEqualTo(1)
        assertThat(result.getRevenueAmount()).isEqualTo(Rank.NONE.getPrize())
    }
}