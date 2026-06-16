package org.example.ladder.view

import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.ladder.constant.ErrorMessage
import org.example.ladder.domain.Player
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class ValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = ["12345", "1234,12345"])
    fun validatePlayerNames_shouldSuccess_whenValidPlayerName(playerNames: String) {
        // given
        val validator = Validator()
        // when
        assertThatCode { validator.validatePlayerNames(playerNames) }
            // then
            .doesNotThrowAnyException()
    }


    @ParameterizedTest
    @ValueSource(strings = ["", " ", "  ", "123456", "12345, 12345"])
    fun validatePlayerNames_shouldThrow_whenInvalidPlayerName(playerNames: String) {
        // given
        val validator = Validator()
        // when

        assertThatThrownBy { validator.validatePlayerNames(playerNames) }

            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.INVALID_PLAYER_NAME)
    }

    @ParameterizedTest
    @CsvSource(delimiter = ';', value = ["12345;1", "1234,12345;2"])
    fun validateRewords_shouldSuccess_whenValidRewords(playerNames: String, size: Int) {
        // given
        val validator = Validator()
        // when
        assertThatCode { validator.validateRewords(playerNames, size) }
            // then
            .doesNotThrowAnyException()
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = ["12345;2", "12345,12345,12345;2", "123456,12345;2"])
    fun validateRewords_shouldThrow_whenInvalidRewords(playerNames: String, size: Int) {
        // given
        val validator = Validator()
        // when

        assertThatThrownBy { validator.validateRewords(playerNames, size) }

            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.INVALID_REWORD_NAME)
    }

    @Test
    fun validateTargetPlayer_shouldSuccess_whenValidTargetName() {
        // given
        val players = listOf(Player("test"), Player("ks"))
        val targetPlayer = "test"
        val validator = Validator()
        // when
        assertThatCode { validator.validateTargetPlayer(targetPlayer, players) }
            // then
            .doesNotThrowAnyException()
    }


    @Test
    fun validateTargetPlayer_shouldThrow_whenInvalidTargetName() {
        // given
        val players = listOf(Player("test"), Player("ks"))
        val targetPlayer = "none"
        val validator = Validator()
        // when
        assertThatCode { validator.validateTargetPlayer(targetPlayer, players) }

            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ErrorMessage.INVALID_TARGET_PLAYER)
    }
}