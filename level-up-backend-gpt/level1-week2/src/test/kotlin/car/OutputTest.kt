package car

import org.assertj.core.api.Assertions.assertThat
import org.example.car.Car
import org.example.car.OutputView
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class OutputTest {

    @Test
    fun 출력기는_경주_상황을_출력한다() {
        // given
        val pobiCar = Car("pobi", 2)
        val woniCar = Car("woni", 1)
        val junCar = Car("jun", 3)
        val outputView = OutputView()

        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        // when
        outputView.printRace(listOf(pobiCar, woniCar, junCar))

        // then
        val lineSeparator = System.lineSeparator()
        val printedOutput = outContent.toString().trim()
        assertThat(printedOutput).isEqualTo("pobi : --$lineSeparator" + "woni : -$lineSeparator" + "jun : ---")

        System.setOut(System.out)
    }

    @Test
    fun 최종_결과를_출력한다() {
        // given
        val pobiCar = Car("pobi", 3)
        val woniCar = Car("woni", 3)
        val junCar = Car("jun", 3)
        val outputView = OutputView()

        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))
        // when
        outputView.printWinner(listOf(pobiCar, woniCar, junCar))
        // then

        val printedOutput = outContent.toString().trim()
        assertThat(printedOutput).isEqualTo("최종 우승자: pobi, woni, jun")

        System.setOut(System.out)
    }
}