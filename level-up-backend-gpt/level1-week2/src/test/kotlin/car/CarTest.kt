package car

import org.assertj.core.api.Assertions.assertThat
import org.example.car.Car
import org.junit.jupiter.api.Test

class CarTest {

    @Test
    fun 자동차_전진은_4이상이어야_한다() {
        // given
        val car = Car("test car")

        // when
        car.drive(3)
        car.drive(4)
        car.drive(3)
        car.drive(5)

        // then
        assertThat(car.distance).isEqualTo(2)
    }
}