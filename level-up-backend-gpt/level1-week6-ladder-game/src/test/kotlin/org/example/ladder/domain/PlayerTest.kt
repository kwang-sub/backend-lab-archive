package org.example.ladder.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.ladder.constant.ErrorMessage
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {

    @Test
    fun constructor_shouldSuccess_whenValidName() {
        // given
        val name = "kwang"

        // when
        val player = Player(name)

        // then
        assertThat(player.name == name).isTrue
    }

    @ParameterizedTest
    @ValueSource(strings = ["", " ", "  ", "123456"])
    fun constructor_shouldFail_whenInvalidName(name: String) {
        // given

        // when
        assertThatThrownBy { Player(name) }

            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.INVALID_PLAYER_NAME)
    }
}

