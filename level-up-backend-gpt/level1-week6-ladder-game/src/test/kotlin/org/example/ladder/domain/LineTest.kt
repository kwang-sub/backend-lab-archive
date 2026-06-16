package org.example.ladder.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.ladder.constant.ErrorMessage
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
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.INVALID_LINE_MINIMUM_POINT_COUNT)
    }
}

