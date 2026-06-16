package org.example.ladder.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.ladder.constant.ErrorMessage
import org.example.ladder.domain.*
import org.junit.jupiter.api.Test

class LadderGameServiceTest {

    @Test
    fun playGame_shouldSuccess_whenStartLeftPoint() {
        // given
        val ladder = Ladder(
            listOf(
                Line(listOf(Point(true), Point(true), Point(false))),
                Line(listOf(Point(true), Point(false), Point(true))),
                Line(listOf(Point(true), Point(true), Point(false))),
            )
        )
        val players = listOf(Player("A"), Player("B"), Player("C"), Player("D"))
        val rewords = listOf(Reword("1"), Reword("2"), Reword("3"), Reword("4"))
        val service = LadderGameService()

        // when
        val result = service.playGame(ladder, players, rewords, "C")

        // then
        assertThat(result.name).isEqualTo("3")
    }

    @Test
    fun playGame_shouldSuccess_whenStartRightPoint() {
        // given
        val ladder = Ladder(
            listOf(
                Line(listOf(Point(true), Point(true), Point(false))),
                Line(listOf(Point(true), Point(false), Point(true))),
                Line(listOf(Point(true), Point(true), Point(false))),
            )
        )
        val players = listOf(Player("A"), Player("B"), Player("C"), Player("D"))
        val rewords = listOf(Reword("1"), Reword("2"), Reword("3"), Reword("4"))
        val service = LadderGameService()

        // when
        val result = service.playGame(ladder, players, rewords, "B")

        // then
        assertThat(result.name).isEqualTo("4")
    }

    @Test
    fun playGame_shouldThrow_whenInvalidTargetName() {
        // given
        val ladder = Ladder(
            listOf(
                Line(listOf(Point(true), Point(true), Point(false))),
                Line(listOf(Point(true), Point(false), Point(true))),
                Line(listOf(Point(true), Point(true), Point(false))),
            )
        )
        val players = listOf(Player("A"), Player("B"), Player("C"), Player("D"))
        val rewords = listOf(Reword("1"), Reword("2"), Reword("3"), Reword("4"))
        val service = LadderGameService()

        // when
        assertThatThrownBy { service.playGame(ladder, players, rewords, "none") }
            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.INVALID_TARGET_PLAYER)
    }


}