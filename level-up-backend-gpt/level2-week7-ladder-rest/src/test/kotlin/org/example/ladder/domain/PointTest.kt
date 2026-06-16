package org.example.ladder.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointTest {

    @Test
    fun constructor_shouldSuccess_whenIsRightConnectFlag() {
        // given
        val isRightConnectFlag = true
        // when
        val point = Point(isRightConnectFlag)
        // then
        assertThat(point.isRightConnect).isTrue
    }
}

