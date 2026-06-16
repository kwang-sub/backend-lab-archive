package org.example.ladder.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.ladder.constant.DomainErrorCode
import org.example.ladder.exception.DomainException
import org.junit.jupiter.api.Test

class LadderTest {

    @Test
    fun constructor_shouldSuccess_whenSameSizeLines() {
        // given
        val lines = listOf(
            Line(listOf(Point(true), Point(false))),
            Line(listOf(Point(false), Point(true)))
        )

        // when
        val ladder = Ladder(lines)

        // then
        assertThat(ladder.lines).isEqualTo(lines)
    }

    @Test
    fun constructor_shouldThrow_whenNotSameSizeLines() {
        // given
        val lines = listOf(
            Line(listOf(Point(true))),
            Line(listOf(Point(false), Point(true)))
        )

        // when
        assertThatThrownBy { Ladder(lines) }

        // then
            .isInstanceOf(DomainException::class.java)
            .hasMessage(DomainErrorCode.NOT_SAME_SIZE_LINES.message)
    }
}

