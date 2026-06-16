package org.example.ladder.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.ladder.constant.DomainErrorCode
import org.example.ladder.exception.DomainException
import org.junit.jupiter.api.Test

class LineTest {

    @Test
    fun constructor_shouldSuccess_whenPointCount() {
        // given
        val points = listOf(Point(true), Point(false))

        // when
        val line = Line(points)

        // then
        assertThat(line.points).isEqualTo(points)
    }

    @Test
    fun constructor_shouldThrow_whenEmptyPoints() {
        // given
        val points = emptyList<Point>()

        // when
        assertThatThrownBy { Line(points) }

            // then
            .isInstanceOf(DomainException::class.java)
            .hasMessage(DomainErrorCode.INVALID_LINE_MINIMUM_POINT_COUNT.message)
    }
}

