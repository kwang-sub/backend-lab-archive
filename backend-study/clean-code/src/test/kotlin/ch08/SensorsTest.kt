package ch08

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException


class SensorsTest {
    @Test
    fun 센서_객체를_생성할_수_있다() {
        // given
        val sensors: Sensors = Sensors.create(1L)
        // when

        //then
        Assertions.assertThat(sensors).isNotEqualTo(null)
    }

    @Test
    fun 센서는_id_값으로_조회하는_기능을_제공한다() {
        // given
        val sensorsMap = SensorsMap()
        val sensors: Sensors = Sensors.create(1L)
        // when
        sensorsMap.add(sensors)
        val findSensors: Sensors = sensorsMap.getById(1L)
        //then
        Assertions.assertThat(findSensors.id).isEqualTo(1L)
    }

    @Test
    fun getById_메서드는_자신의_id_값이_아닌_경우_예외를_던진다() {
        // given
        val sensorsMap = SensorsMap()
        // when

        //then
        Assertions.assertThatThrownBy { sensorsMap.getById(1L) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}