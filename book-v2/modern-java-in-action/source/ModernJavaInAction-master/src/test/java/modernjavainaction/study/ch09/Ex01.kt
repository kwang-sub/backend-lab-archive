package modernjavainaction.study.ch09

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Ex01 {
    @Test
    fun testMoveRightBy() {
        val p1 = Point(5, 5)
        val p2 = p1.moveRightBy(10)
        assertThat(p2.getX()).isEqualTo(15)
        assertThat(p1.getX()).isEqualTo(5)
    }

    @Test
    fun testComparingTwoPoints() {
        val p1 = Point(10, 15)
        val p2 = Point(10, 20)
        val result = Point.compareByXAndThenY().compare(p1, p2)
        assertThat(result < 0).isTrue()
    }
}