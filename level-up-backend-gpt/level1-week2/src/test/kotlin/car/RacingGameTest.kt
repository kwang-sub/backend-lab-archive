package car

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.car.Car
import org.example.car.RacingGame
import org.example.car.RandomNumGenerator
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class RacingGameTest {

    @Mock
    lateinit var randomGenerator: RandomNumGenerator

    @Test
    fun 레이스를_진행할_수_있다() {
        // given
        val pobiCar = Car("pobi")
        val woniCar = Car("woni")
        val junCar = Car("jun")
        val racingGame = RacingGame(listOf(pobiCar, woniCar, junCar), 3, randomGenerator)
        `when`(randomGenerator.generate())
            .thenReturn(5)

        // when
        racingGame.race()

        // then
        assertThat(pobiCar.distance).isEqualTo(1)
        assertThat(woniCar.distance).isEqualTo(1)
        assertThat(junCar.distance).isEqualTo(1)

        verify(randomGenerator, times(3)).generate()
    }

    @Test
    fun 레이스가_끝나면_더_이상_진행할_수_없다() {
        // given
        val pobiCar = Car("pobi")
        val woniCar = Car("woni")
        val junCar = Car("jun")
        val racingGame = RacingGame(listOf(pobiCar, woniCar, junCar), 1, randomGenerator)

        racingGame.race()
        // when
        assertThatThrownBy { racingGame.race() }

        // then
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("경주가 끝났습니다.")
    }


    @Test
    fun 레이스_우승자를_찾을_수_있다() {
        // given
        val pobiCar = Car("pobi")
        val woniCar = Car("woni")
        val junCar = Car("jun")
        val cars = listOf(pobiCar, woniCar, junCar)
        val racingGame = RacingGame(cars, 3, randomGenerator)
        `when`(randomGenerator.generate())
            .thenReturn(5)

        racingGame.race()
        racingGame.race()
        racingGame.race()
        // when

        val winners = racingGame.getWinners()

        // then
        assertThat(winners).containsAll(cars)

        verify(randomGenerator, times(9)).generate()
    }

    @Test
    fun 레이스가_끝나기전에_우승자를_찾을_수_없다() {
        // given
        val pobiCar = Car("pobi")
        val woniCar = Car("woni")
        val junCar = Car("jun")
        val cars = listOf(pobiCar, woniCar, junCar)
        val racingGame = RacingGame(cars, 3, randomGenerator)

        // when
        assertThatThrownBy { racingGame.getWinners() }

        // then
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("경주가 끝나지 않았습니다.")
    }
}