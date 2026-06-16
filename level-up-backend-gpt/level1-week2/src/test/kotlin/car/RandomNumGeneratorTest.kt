package car

import org.assertj.core.api.Assertions.assertThat
import org.example.car.RandomNumGenerator
import org.junit.jupiter.api.RepeatedTest

class RandomNumGeneratorTest {

    @RepeatedTest(value = 10)
    fun 랜덤생성기_0에서_9까지_생성() {
        // given
        val randomNumGenerator = RandomNumGenerator()
        // when
        val num = randomNumGenerator.generate()
        // then
        assertThat(num).isBetween(0, 9)
    }
}