package org.example.lotto

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.lotto.domain.LotteryBundle
import org.example.lotto.domain.LotteryTicket
import org.example.lotto.domain.LotteryNumber
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LotteryTicketTest {


    @Test
    fun createLottery_shouldSucceed_whenValidInput() {
        // given
        val amount = 2_000

        val lotteryTicket1 = LotteryTicket(
            lotteryNumber = LotteryNumber(listOf(1, 2, 3, 4, 5, 6)),
            order = 1
        )

        val lotteryTicket2 = LotteryTicket(
            lotteryNumber = LotteryNumber(listOf(1, 2, 3, 4, 5, 6)),
            order = 2
        )

        val lotteryInfoList = listOf(
            lotteryTicket1,
            lotteryTicket2
        )


        // when
        val lotteryBundle = LotteryBundle(amount, lotteryInfoList)

        // then
        assertThat(lotteryBundle).isNotNull
        assertThat(lotteryBundle.amount).isEqualTo(amount)
        assertThat(lotteryBundle.lotteryTicketList.size).isEqualTo(2)
    }

    @Test
    fun createLottery_shouldThrow_whenAmountDoesNotMatchNumbers() {
        // given
        val amount = 2_000

        val lotteryTicket = LotteryTicket(
            lotteryNumber = LotteryNumber(listOf(1, 2, 3, 4, 5, 6)),
            order = 1
        )

        // when
        assertThatThrownBy { LotteryBundle(amount, listOf(lotteryTicket)) }
            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("로또 금액 또는 수량이 올바르지 않습니다.")
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 1, 1001])
    fun createLottery_shouldThrow_whenInvalidAmount(amount: Int) {
        // given
        val lotteryTicket = LotteryTicket(
            lotteryNumber = LotteryNumber(listOf(1, 2, 3, 4, 5, 6)),
            order = 1
        )
        // when
        assertThatThrownBy { LotteryBundle(amount, listOf(lotteryTicket)) }
            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("로또 금액 또는 수량이 올바르지 않습니다.")
    }

    @Test
    fun createLottery_shouldThrow_whenDuplicateOrderLotteryInfo() {
        // given
        val amount = 2_000

        val lotteryTicket1 = LotteryTicket(
            lotteryNumber = LotteryNumber(listOf(1, 2, 3, 4, 5, 6)),
            order = 1
        )

        val lotteryTicket2 = LotteryTicket(
            lotteryNumber = LotteryNumber(listOf(1, 2, 3, 4, 5, 6)),
            order = 1
        )

        // when
        assertThatThrownBy { LotteryBundle(amount, listOf(lotteryTicket1, lotteryTicket2)) }

            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("로또 번호는 중복될 수 없습니다.")
    }
}