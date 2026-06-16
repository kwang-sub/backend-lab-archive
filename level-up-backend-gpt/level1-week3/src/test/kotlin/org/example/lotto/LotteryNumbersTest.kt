package org.example.lotto

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.lotto.domain.LotteryNumber
import org.example.lotto.domain.Rank
import org.example.lotto.domain.WinningNumbers
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.Test

class LotteryNumbersTest {

    @Test
    fun createLotteryNumbers_shouldSucceed_whenBetween1to45Numbers() {
        // given

        // when
        val lotteryNumber = LotteryNumber(listOf(1, 2, 3, 4, 5, 45))

        // then
        assertThat(lotteryNumber).isNotNull
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = ["1,1,1,1,1,1", "1,1,3,4,5,6", "1,2,6,4,5,6"])
    fun createLotteryNumbers_shouldThrow_whenDuplicateNumbers(lotteryNumbers: String) {
        // given
        val lotteryNumbers = lotteryNumbers.split(",").map { it.toInt() }

        // when
        assertThatThrownBy { LotteryNumber(lotteryNumbers) }

            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("로또 번호는 중복될 수 없습니다.")

    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = ["0,2,3,4,5,6", "1,2,3,4,5,46", "-1,2,3,4,5,6"])
    fun createLotteryNumbers_shouldThrow_whenOutOfRangeNumbers(lotteryNumbers: String) {
        // given
        val lotteryNumbers = lotteryNumbers.split(",").map { it.toInt() }

        // when
        assertThatThrownBy { LotteryNumber(lotteryNumbers) }

            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("로또 번호는 1부터 45까지의 숫자여야 합니다.")

    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = ["1,2,3,4,5", "1,2,3,4,5,6,7"])
    fun createLotteryNumbers_shouldThrow_whenNotEnoughSizeNumbers(lotteryNumbers: String) {
        // given
        val lotteryNumbers = lotteryNumbers.split(",").map { it.toInt() }

        // when
        assertThatThrownBy { LotteryNumber(lotteryNumbers) }

            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("로또 번호는 6개입니다.")
    }

    @ParameterizedTest
    @ValueSource(ints = [7, 8, 45])
    fun setBonusNumber_shouldSucceed_whenBetween1to45Number(bonusNumber: Int) {
        // given
        val lotteryNumber = LotteryNumber(listOf(1, 2, 3, 4, 5, 6))

        // when
        lotteryNumber.setBonusNumber(bonusNumber)

        // then
        assertThat(lotteryNumber.bonusNumber).isEqualTo(bonusNumber)
        assertThat(lotteryNumber.isComplete()).isTrue
    }

    @Test
    fun setBonusNumber_shouldThrow_whenDuplicateNumber() {
        // given
        val lotteryNumber = LotteryNumber(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = 1

        // when
        assertThatThrownBy { lotteryNumber.setBonusNumber(bonusNumber) }

            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("로또 번호는 중복될 수 없습니다.")
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 46])
    fun setBonusNumber_shouldThrow_whenNotBetween1to45Number(bonusNumber: Int) {
        // given
        val lotteryNumber = LotteryNumber(listOf(1, 2, 3, 4, 5, 6))

        // when
        assertThatThrownBy { lotteryNumber.setBonusNumber(bonusNumber) }

            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("로또 번호는 1부터 45까지의 숫자여야 합니다.")
    }

    @Test
    fun setBonusNumber_shouldThrow_whenAlreadySetBonusNumber() {
        // given
        val lotteryNumber = LotteryNumber(listOf(1, 2, 3, 4, 5, 6))
        lotteryNumber.setBonusNumber(7)

        // when
        assertThatThrownBy { lotteryNumber.setBonusNumber(8) }

            // then
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("보너스 번호는 한 번만 설정할 수 있습니다.")
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
    fun determineRankBy_shouldSucceed_withVariousRanks(
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

        // when
        val rank = lotteryNumbers.determineRankBy(winningNumbers)

        // then
        assertThat(rank).isEqualTo(expectedRank)
    }
}