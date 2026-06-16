package car

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.car.Car
import org.example.car.InputView
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class InputViewTest {

    @Test
    fun 경주자동차_이름_입력_예시_출력테스트() {
        // given
        val input = "car1,car2\n" // 사용자가 콘솔에 입력할 문자열 (개행 포함)
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        val inputView = InputView()

        // when
        val result = inputView.inputCar()

        // then
        val printedOutput = outContent.toString().trim()
        assertThat(printedOutput).isEqualTo("경주할 자동차 이름을 입력하세요(이름은 쉼표(,) 기준으로 구분)")

        System.setOut(System.out)
        System.setIn(System.`in`)
    }

    @Test
    fun 경주는_자동차_이름_입력_테스트() {
        // given
        val input = "car1,car2\n" // 사용자가 콘솔에 입력할 문자열 (개행 포함)
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)
        val inputView = InputView()

        // when
        val result = inputView.inputCar()

        // then
        val expect = listOf(Car("car1"), Car("car2"))
        assertThat(result).containsAll(expect)

        System.setIn(System.`in`)
    }

    @ParameterizedTest
    @ValueSource(strings = ["  ", ""])
    fun 경주_자동차_이름_입력_예외_테스트(input: String?) {
        // given
        val inputStream = ByteArrayInputStream(input?.toByteArray())
        System.setIn(inputStream)
        val inputView = InputView()
        // when
        assertThatThrownBy { inputView.inputCar() }
            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("잘못된 자동차 이름입니다.")
    }

    @Test
    fun 경주_횟수_입력_테스트() {
        // given
        val input = "4\n" // 사용자가 콘솔에 입력할 문자열 (개행 포함)
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val inputView = InputView()

        // when
        val result = inputView.inputRaceCount()

        // then
        assertThat(result).isEqualTo(4)

        System.setIn(System.`in`)
    }

    @Test
    fun 경주_횟수_입력_예외_테스트() {
        // given
        val input = "4ㅁㅎ\n" // 사용자가 콘솔에 입력할 문자열 (개행 포함)
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val inputView = InputView()

        // when
        assertThatThrownBy { inputView.inputRaceCount() }

            // then
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("숫자 형식이 아닙니다.")
        System.setIn(System.`in`)
    }


}