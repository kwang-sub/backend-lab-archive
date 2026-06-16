package ch06.item34

import org.junit.jupiter.api.Test

class PlanetTest {

    @Test
    fun 중력_테스트() {
        // given
        val earthWeight: Double = 185.0
        val mass = earthWeight / Planet.EARTH.surfaceGravity
        // when
        for (p: Planet in Planet.entries) {
            println("${p}에서의 무게는 ${p.surfaceWeight(mass)}이다")
        }
        // then
    }
}